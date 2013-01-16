package de.ingoreschke.jedis;

import org.junit.Before;

import de.ingoreschke.redis.TestRedisChat;

public class TestJedisChat extends TestRedisChat {

	private JedisChat cut;

	@Override
	@Before
	public void setUp(){
		cut = new JedisChat();
	}
}
