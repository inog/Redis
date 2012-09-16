package de.ingoreschke.redis;

import org.junit.Assert;
import org.junit.Test;

public class TestRedis {
	
	@Test
	public void testGet(){
		IRedis redis = new MockRedis();
		redis.get("test");
	}
	
	@Test
	public void testGet_keyDontExits() {
		IRedis redis = new MockRedis();
		Assert.assertEquals(null, redis.get("test"));
	}
	

	@Test
	public void testGet_keyExists(){
		String key = "testkey";
		String expected = "test";
		IRedis redis = new MockRedis(); 
		redis.set(key, expected);
		Assert.assertEquals(expected, redis.get(key));
	}
	
	@Test
	public void testSet(){
		IRedis redis = new MockRedis();
		redis.set("testKey","testValue");
	}
	
	@Test
	public void testSet_String(){
		IRedis redis = new MockRedis();
		String key = "testkey";
		String expected = "testValue";
		redis.set(key, expected);
		Assert.assertEquals(expected, redis.get(key));
	}
	
	
	/* I should test when value is not a string.
	@Test (expected )
	public void testSet_NoString(){
		IRedis redis = new MockRedis();
		
	}
	*/
}
