package de.ingoreschke.jedis;

import org.junit.Before;

import de.ingoreschke.redis.TestRedisHash;

public class TestJedisHash extends TestRedisHash {
	
	@Override
	@Before
	public void setUp(){
		redis = new JedisRedis();
		
		JedisRedis j = (JedisRedis) redis;
		j.flushDB();
	}
}
