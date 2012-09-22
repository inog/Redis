package de.ingoreschke.redis;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class TestRedisHash {
	private IRedis redis;
		
	@Before	
	public void setUp(){
		redis = new Redis();
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
	
}
