package de.ingoreschke.redis.mocks;

import java.util.List;

import de.ingoreschke.redis.internal.IChatListener;

public class MockChatClient implements IChatListener {
	private final String name;
	private String message;

	public MockChatClient(final String name) {
		this.name = name;
	}

	@Override
	public void onMessage(final String channel, final String message) {
		System.out.println(name + " received a message : " + message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String getLastMessage(final String channel) {
		return message;
	}

	@Override
	public List<String> getAllMessages(final String channel) {
		return null;
	}
}
