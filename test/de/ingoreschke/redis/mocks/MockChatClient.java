package de.ingoreschke.redis.mocks;

import de.ingoreschke.redis.internal.IChatListener;

public class MockChatClient implements IChatListener {
	private final String name;
	private String message;
	
	public MockChatClient(final String name) {
		this.name = name;
	}

	@Override
	public void onMessage(String channel, String message) {
		System.out.println(name + " received a message : " + message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
