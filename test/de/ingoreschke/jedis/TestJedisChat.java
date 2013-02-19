package de.ingoreschke.jedis;

import org.junit.Before;

import de.ingoreschke.redis.TestRedisChat;

public class TestJedisChat extends TestRedisChat {

	protected JedisChat cut;
	private JedisChatListener client;

	@Override
	@Before
	public void setUp(){
		cut = new JedisChat();
		client = new JedisChatListener();
	}

}
