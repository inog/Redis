package de.ingoreschke.jedis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestJedisChatListener {
	private JedisChatListener cut;

	@Before
	public void setUp(){
		cut = new JedisChatListener();
	}

	@Test
	public void testGetLastMessage() {
		String channel = "mySportChannel";
		cut.onMessage(channel, "welcome to my Sport channel");
		cut.onMessage(channel, "second message");
		String result = cut.getLastMessage(channel);
		assertEquals("second message", result);
	}

	@Test
	public void testGetLastMessage_NoMsg() {
		String channel = "mySportChannel";
		String result = cut.getLastMessage(channel);
		assertEquals("", result);
	}

	@Test
	public void testGetLastMessage_NoChannel() {
		String result = cut.getLastMessage("unknown");
		assertEquals("", result);
	}

	@Test
	public void testGetAllMesssages(){
		String channel = "mySportChannel";
		cut.onMessage(channel, "welcome to my Sport channel");
		cut.onMessage(channel, "second message");
		cut.onMessage(channel, "third msg");
		cut.onMessage(channel, "oh this one is realy long.");
		List<String> result = cut.getAllMessages(channel);
		assertEquals(4, result.size());
		assertTrue(result.contains("third msg"));
	}
}
