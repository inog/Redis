package de.ingoreschke.redis.mocks;

import java.util.HashSet;
import java.util.Set;

import de.ingoreschke.redis.internal.IChatListener;
import de.ingoreschke.redis.internal.IRedisChat;

public class MockIredisChat implements IRedisChat {
	
	
	private final Set<IChatListener> listeners;

	
	public MockIredisChat(){
		listeners = new HashSet<>();
	}


	@Override
	public int publish(String channel, String message) {
		int count = 0;
		for (IChatListener listener : listeners) {
			listener.onMessage(channel, message);
			count++;
		}
		return count;
	}

	@Override
	public boolean subscribe(IChatListener listener, String... channel) {
		boolean isAdded = listeners.add(listener);
		return isAdded;
	}

	@Override
	public boolean unsubscribe(String... channel) {
		// TODO Auto-generated method stub
		return false;
	}

}
