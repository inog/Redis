package de.ingoreschke.redis.mocks;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.ingoreschke.redis.internal.IChatListener;
import de.ingoreschke.redis.internal.IRedisChat;

public class MockIredisChat implements IRedisChat {

	private Set<IChatListener> chatClients;
	private Set<String> channels;


	public MockIredisChat(){
		chatClients = new HashSet<>();
		channels = new HashSet<>();
	}


	@Override
	public int publish(final String channel, final String message) {
		int count = 0;
		for (IChatListener listener : chatClients) {
			listener.onMessage( channel, message);
			count++;
		}
		return count;
	}

	@Override
	public boolean subscribe(final IChatListener chatClient, final String... channel) {
		for (String ch : channel) {
			channels.add(ch);
		}
		boolean isAdded = chatClients.add(chatClient);
		return isAdded;
	}

	@Override
	public boolean unsubscribe(final IChatListener chatClient, final String... channel) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public List<String> channelList() {
		List<String> returnList = new ArrayList<>();
		for (String channel : channels) {
			returnList.add(channel);
		}
		return returnList;
	}

}
