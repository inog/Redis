package de.ingoreschke.redis.mocks;

import java.util.HashSet;
import java.util.Set;

import de.ingoreschke.redis.internal.IChatListener;
import de.ingoreschke.redis.internal.IRedisChat;

public class MockIredisChat implements IRedisChat {


	private final Set<IChatListener> listeners;
	private final String channel;



	public MockIredisChat(final String channel){
		listeners = new HashSet<>();
		this.channel = channel;
	}


	@Override
	public int publish(final String channel, final String message) {
		int count = 0;
		for (IChatListener listener : listeners) {
			listener.onMessage(channel, message);
			count++;
		}
		return count;
	}

	@Override
	public boolean subscribe(final IChatListener listener, final String... channel) {
		boolean isAdded = listeners.add(listener);
		return isAdded;
	}

	@Override
	public boolean unsubscribe(final String... channel) {
		// TODO Auto-generated method stub
		return false;
	}

}
