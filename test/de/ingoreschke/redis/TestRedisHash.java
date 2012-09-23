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
	
	@Test
	public void testHSet(){
		String key = "key1";
		String field = "field1";
		Assert.assertEquals(1, redis.hSet(key, field, "something"));
		Assert.assertEquals(0, redis.hSet(key, field, "something other stuff"));
	}
	
	@Test
	public void testHSet_FieldExist(){
		String key = "key1";
		String field = "field1";
		String value1 = "value1";
		String value2 = "my crazy value 2";
		redis.hSet(key, field, value1);
		Assert.assertEquals(0, redis.hSet(key, field, value2));	
		Assert.assertEquals(value2, redis.hGet(key, field));
	}
	
}
