package de.ingoreschke.redis;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.ingoreschke.redis.mocks.Redis;

public class TestRedisString {
	protected IRedis redis;
	
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
		redis.set(key, String.valueOf(in));
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
	
	@Test (expected = IllegalArgumentException.class)
	public void testIncr_valueToBig(){
		String key = "count";
		long l =  Long.MAX_VALUE;
		String value = String.valueOf(l);
		redis.set(key, value);
		redis.incr(key);//this should throw an exception.
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testIncr_valueToSmall(){
		String key = "count";
		long l =  Long.MIN_VALUE -2;
		String value = String.valueOf(l);
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
	
	@Test
	public void testMSet_returningString(){
		String expected = "OK";
		Map<String, String> m = createExampleMap();
		Assert.assertEquals(expected, redis.mSet(m));
	}
	
	@Test
	public void testMSet_KeyExists(){
		redis.set("key3", "My special value");
		redis.mSet(createExampleMap());
		String expected = "value3";
		Assert.assertEquals(expected, redis.get("key3"));
	}
	
		
	/* Helper functions */
	private Map<String, String> createExampleMap(){
		Map<String, String> m = new HashMap<>();
		m.put("key1", "value1");
		m.put("key2", "value2");
		m.put("key3", "value3");
		m.put("key4", "value4");
		return m;
	}
}
