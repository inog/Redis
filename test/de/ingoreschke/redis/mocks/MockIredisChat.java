package de.ingoreschke.redis.mocks;

import de.ingoreschke.redis.internal.IChatListener;
import de.ingoreschke.redis.internal.IRedisChat;

public class MockIredisChat implements IRedisChat {
	
	
	private IChatListener listener;
	private String message;

	public String getMessage() {
		return message;
	}

	@Override
	public Integer publish(String channel, String message) {
		listener.onMessage(channel, message);
		this.message = message;
		return null;
	}

	@Override
	public boolean subscribe(IChatListener listener, String... channel) {
		this.listener = listener;
		return true;
	}

	@Override
	public boolean unsubscribe(String... channel) {
		// TODO Auto-generated method stub
		return false;
	}

}
