package de.ingoreschke.jedis;

import org.junit.Before;
import org.junit.Test;

import de.ingoreschke.redis.TestRedisChat;

public class TestJedisChat extends TestRedisChat {

	protected JedisChat cut;

	@Override
	@Before
	public void setUp(){
		cut = new JedisChat();
	}


	@Test
	public void testGetAllMesssages(){
		String channel = "mySportChannel";
		cut.publish(channel, "welcome to my Sport channel");
		cut.publish(channel, "second message");
		cut.publish(channel, "third msg");
		cut.publish(channel, "oh this one is realy long.");
	}
}
