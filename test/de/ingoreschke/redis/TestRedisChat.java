package de.ingoreschke.redis;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import de.ingoreschke.redis.mocks.Redis;

public class TestRedisChat {
	
	private IRedis redis;
	
	@Before
	public void setUp(){
		redis = new Redis();
	}
	
	@Test
	public void testPublishNoClient(){
		Integer expected = 0;
		//Assert.assertEquals(expected, redis.publish());
	}
	
	@Test
	public void testPublishTwoClients(){
		
	}
	@Test
	public void testPublishMessage(){
		
	}
}