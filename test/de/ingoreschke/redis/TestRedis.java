package de.ingoreschke.redis;



import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestRedis {
	private IRedis redis;
	
	@Before	
	public void setUp(){
		redis = new Redis();
	}
	
	
	@Test
	public void testGet_keyDontExits() {
		Assert.assertEquals(null, redis.get("test"));
	}

	@Test
	public void testGet_keyExists(){
		String key = "testkey";
		String expected = "test";
		redis.set(key, expected);
		Assert.assertEquals(expected, redis.get(key));
	}
	
	@Test
	public void testSet(){
		String expected = "OK";
		Assert.assertEquals(expected, redis.set("testKey","testValue"));
	}
	
	@Test
	public void testSet_String(){
		String key = "testkey";
		String expected = "testValue";
		redis.set(key, expected);
		Assert.assertEquals(expected, redis.get(key));
	}
	
	
	@Test 
	public void testSet_Object(){
		String key = "testkey";
		Object objIn = new Object(); 
		String expected = objIn.toString();
		redis.set(key, objIn);
		Assert.assertEquals(expected, redis.get(key));
	}

	@Test
	public void testSet_DuplicateKeys(){
		String key = "myKey";
		String expected = "new Value";
		redis.set(key, "orginalValue");
		redis.set(key, expected);
		Assert.assertEquals(expected,redis.get(key) );
	}
	
	@Test
	public void testIncr_keyDontExists(){
		Object result = redis.incr("test");
		Integer expected = 1;
		Assert.assertEquals(expected, result);
	}
	
	@Test
	public void testIncr_valueValid(){
		int in = 5;
		String key = "count";
		redis.set(key, in);
		int expected = Integer.valueOf(++in);
		Assert.assertEquals(expected, redis.incr(key));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testIncr_valueInvalid(){
		String key = "count";
		String value = "Im Not a Number";
		redis.set(key, value);
		redis.incr(key);//this should throw an exception.
	}
	
	@Test
	public void testIncr_returningString() {
		String key = "mykey";
		String expected = "11";
		redis.set(key, "10");
		redis.incr(key);
		Assert.assertEquals(expected, redis.get(key));
	}
	
	@Test
	public void testMGet_KeyDontExist(){
		String key = "notExists";
		List<String> returnList = redis.mGet(key);
		Assert.assertEquals(null, returnList.get(0));
	}
	
	@Test
	public void testMGet_KeysExists(){
		redis.set("key1", "value1");
		redis.set("key2", "value2");
		redis.set("key3", "value3");
		Assert.assertEquals(3, redis.mGet("key1", "key2", "key3").size());
	}
	
	@Test
	public void testMGet_KeysWithSpace(){
		redis.set("key 1", "value1");
		redis.set("key 2", "value2");
		redis.set("key 3", "value3");
		Assert.assertEquals(3, redis.mGet("key 1", "key 2", "key 3").size());
	}
}
