package de.ingoreschke.redis;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import de.ingoreschke.redis.internal.IRedisChat;
import de.ingoreschke.redis.mocks.MockChatClient;
import de.ingoreschke.redis.mocks.MockIredisChat;

public class TestRedisChat {

	private IRedisChat cut; //class under test;

	@Before
	public void setUp(){
		cut = new MockIredisChat("TDD");
	}

	@Test
	public void testPublish(){
		String expected = "Message from testPublish";
		MockChatClient client1 = new MockChatClient("client 1");
		cut.subscribe(client1, "dsf");
		cut.publish("dsfs", expected);
		String actual = client1.getMessage();
		assertEquals(expected, actual);
	}

	@Test
	public void testMultipleClients(){
		String expected = "Message to more than 1 Client";
		MockChatClient cl1 = new MockChatClient("cl1");
		MockChatClient cl2 = new MockChatClient("cl2");
		MockChatClient cl3 = new MockChatClient("cl3");
		cut.subscribe(cl1, "");
		cut.subscribe(cl2, "");
		cut.subscribe(cl3, "");
		cut.publish("somechanel", expected);
		assertEquals("cl1 : ",expected,cl1.getMessage());
		assertEquals("cl2 : ",expected,cl2.getMessage());
		assertEquals("cl3 : ",expected,cl3.getMessage());
	}

	@Test
	public void testSubcribeToChannel(){
		String channel = "Testchannel";
		String msg = "hello some msg";
		MockChatClient cl = new MockChatClient("cl1");
		cut.subscribe(cl, channel);
		cut.publish(channel, msg);
		assertEquals(msg, cl.getMessage());
	}

	@Test
	public void testListenToNotSubscribedChannel(){
		String msg = "you should not see me.";
		MockChatClient cl = new MockChatClient("cl");
		cut.subscribe(cl, "SomeFancyChannel");
		cut.publish("otherChannel", msg);
		assertFalse(msg.equals(cl.getMessage()));
	}
}