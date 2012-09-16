package de.ingoreschke.redis;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestRedis {
	private IRedis redis;
	
	@Before	
	public void setUp(){
		redis = new MockRedis();
	}
	
	@Test
	public void testGet(){
		redis.get("test");
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
		redis.set("testKey","testValue");
	}
	
	@Test
	public void testSet_String(){
		String key = "testkey";
		String expected = "testValue";
		redis.set(key, expected);
		Assert.assertEquals(expected, redis.get(key));
	}
	
	
	/* I should test when value is not a string.
	@Test (expected )
	public void testSet_NoString(){
		
	}
	*/
}
