package de.ingoreschke.redis;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.ingoreschke.redis.internal.IRedisChat;
import de.ingoreschke.redis.mocks.MockChatClient;
import de.ingoreschke.redis.mocks.MockIredisChat;

public class TestRedisChat {

	protected IRedisChat cut; //class under test;
	private MockChatClient client;

	@Before
	public void setUp(){
		cut = new MockIredisChat();
		client = new MockChatClient("client");
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
		cut.subscribe(client, channel);
		cut.publish(channel, msg);
		assertEquals(msg, client.getLastMessage(channel));
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
		cut.publish(channel, "new Message");
		assertEquals(msg, cl.getMessage());
		cut.unsubscribe(cl, "channel_dontExists", "channel_dontExists2" );
		assertEquals(msg, cl.getMessage());
	}

	@Test
	public void testChannelList(){
		MockChatClient cl = new MockChatClient("client1");
		String channel1 = "Sport";
		String channel2 = "Java";
		String channel3 = "TDD - Developer";
		String channel4 = "JUnit";
		cut.subscribe(cl, channel1, channel2, channel3, channel4);
		List<String> result = cut.channelList();
		assertTrue(result.contains(channel1));
		assertTrue(result.contains(channel2));
		assertTrue(result.contains(channel3));
		assertTrue(result.contains(channel4));
	}


}