package de.ingoreschke.redis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class TestRedisHash {
	
	private static final String KEY = "mykey";
	private static final String FIELD1 = "field1";
	private static final String FIELD2 = "field2";
	private static final String FIELD3 = "field3";
	private static final String VALUE1 = "value1";
	private static final String VALUE2 = "value2";
	private static final String VALUE3 = "value3";
	
	private IRedis redis;
		
	@Before	
	public void setUp(){
		redis = new Redis();
		redis.hSet(KEY, FIELD1, VALUE1);
		redis.hSet(KEY, FIELD2, VALUE2);
		redis.hSet(KEY, FIELD3, VALUE3);
	}
	
	
	@Test
	public void testHGet_KeyDontExists(){
		Assert.assertEquals(null, redis.hGet("doNotExist", "someField"));
	}
	
	@Test
	public void testHGet_FieldDontExists(){
		String key = "key1";
		redis.hSet(key, "field1", "someValue");
		Assert.assertEquals(null, redis.hGet(key, "doNotExist"));
	}
	
	@Test
	public void testHGet(){
		String key = "key1";
		String field = "field1";
		String expected = "value1";
		redis.hSet(key, field, expected);
		Assert.assertEquals(expected, redis.hGet(key, field));
	}
	
	@Test
	public void testHSet(){
		String key = "key1";
		String field = "field1";
		Assert.assertEquals(1, redis.hSet(key, field, "something"));
		Assert.assertEquals(0, redis.hSet(key, field, "something other stuff"));
	}
	
	@Test
	public void testHSet_FieldExist(){
		String myCrazyValue = "my crazy value 2";
		Assert.assertEquals(0, redis.hSet(KEY, FIELD1, myCrazyValue));	
		Assert.assertEquals(myCrazyValue, redis.hGet(KEY, FIELD1));
	}
	
	@Test
	public void testHMGet(){
		List<String> list = redis.hMGet(KEY, FIELD1, FIELD2, FIELD3);
		Assert.assertEquals(3, list.size());
		Assert.assertEquals(VALUE1, list.get(0));
		Assert.assertEquals(VALUE2, list.get(1));
		Assert.assertEquals(VALUE3, list.get(2));
	}
	
	@Test
	public void testHMGet_FieldDontExist(){
		List<String> list = redis.hMGet(KEY, FIELD1, FIELD2, FIELD3, "doNotExist");
		Assert.assertEquals(null, list.get(3));
	}
	
	@Test
	public void testHMGet_KeyDontExist(){
		List <String> list = redis.hMGet("keyThatdoNotExist", FIELD1,FIELD2,FIELD3,"someOtherField");
		Assert.assertEquals(4, list.size());
		Assert.assertEquals(null, list.get(0));
		Assert.assertEquals(null, list.get(1));
		Assert.assertEquals(null, list.get(2));
		Assert.assertEquals(null, list.get(3));
	}
	
	@Test
	public void testHMSet_KeyDontExist(){
		String key = "myKeyThatDoNotExistsbefore";
		Map<String, String> fields = new HashMap<>();
		fields.put(FIELD1, VALUE1);
		fields.put(FIELD2, VALUE2);
		fields.put(FIELD3, VALUE3);
		Assert.assertEquals("OK",redis.hMSet(key, fields));
		Assert.assertEquals(VALUE2, redis.hGet(key, FIELD2));
	}
	
	@Test
	public void testHMSet_KeyExist(){
		String newVal2 = "myNewVal2";
		Map<String, String> fields = new HashMap<>();
		fields.put(FIELD2, newVal2); 
		Assert.assertEquals("OK",redis.hMSet(KEY, fields)); //should overwrite field2
		Assert.assertEquals(newVal2, redis.hGet(KEY, FIELD2));
	}
}
