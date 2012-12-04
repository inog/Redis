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
		cut = new MockIredisChat();
	}

	@Test
	public void testPublish(){
		MockChatClient client1 = new MockChatClient("client 1");
		String channel = "sport";
		cut.subscribe(client1, channel);
		String expected = "Message from testPublish";
		cut.publish(channel, expected);
		String actual = client1.getMessage();
		assertEquals(expected, actual);
	}

	@Test
	public void testMultipleClients(){
		String expected = "Message to more than 1 Client";
		String channel = "Sport";
		MockChatClient cl1 = new MockChatClient("cl1");
		MockChatClient cl2 = new MockChatClient("cl2");
		MockChatClient cl3 = new MockChatClient("cl3");
		cut.subscribe(cl1, channel);
		cut.subscribe(cl2, channel);
		cut.subscribe(cl3, channel);
		cut.publish(channel, expected);
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

	@Test
	public void testUnsubscribe(){
		MockChatClient cl = new MockChatClient("cl");
		String channel = "test";
		String msg = "some Message";
		cut.subscribe(cl, channel);
		cut.publish(channel, msg);
		assertEquals(msg, cl.getMessage());
		cut.unsubscribe(cl, channel);
		assertFalse(msg.equals(cl.getMessage()));
	}
}