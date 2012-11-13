package de.ingoreschke.redis;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.ingoreschke.redis.mocks.MockChatClient;
import de.ingoreschke.redis.mocks.MockIredisChat;

public class TestRedisChat {
	
	/*private IRedis redis;
	
	@Before
	public void setUp(){
		redis = new Redis();
	}**/
	
	@Test
	public void testPublish(){
		String expected = "Message from testPublish";
		MockChatClient client1 = new MockChatClient("client 1");
		MockIredisChat c = new MockIredisChat();
		c.subscribe(client1, "dsf");
		c.publish("dsfs", expected);
		
		String actual = client1.getMessage();
		assertEquals(expected, actual);
	}
}