package de.ingoreschke.jedis;

import org.junit.Before;

import de.ingoreschke.redis.TestRedisString;

public class TestJedisString extends TestRedisString {

	@Override
	@Before
	public void setUp() {
		redis = new JedisRedis();
		((JedisRedis)redis).flushDB();
	}
	
}
