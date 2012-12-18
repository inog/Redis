package de.ingoreschke.redis.mocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.ingoreschke.redis.internal.IChatListener;
import de.ingoreschke.redis.internal.IRedisChat;

public class MockIredisChat implements IRedisChat {

	private Map<String, Set<IChatListener>> chatmap;


	public MockIredisChat(){
		chatmap = new HashMap<>();
	}


	@Override
	public int publish(final String channel, final String message) {
		int count = 0;
		Set<IChatListener> chatClients = chatmap.get(channel);
		if (chatClients != null){
			for (IChatListener listener : chatClients) {
				listener.onMessage( channel, message);
				count++;
			}
		}
		return count;
	}

	@Override
	public void subscribe(final IChatListener chatClient, final String... channel) {

		for (String currentChannel : channel) {
			if(chatmap.containsKey(currentChannel)){
				Set<IChatListener> clients = chatmap.get(currentChannel);
				clients.add(chatClient);
			}else{
				Set<IChatListener> newClientSet = new HashSet<>();
				newClientSet.add(chatClient);
				chatmap.put(currentChannel, newClientSet);
			}
		}
	}

	@Override
	public void unsubscribe(final IChatListener chatClient, final String... channel) {
		for (String currentChannel : channel) {
			if (chatmap.containsKey(currentChannel)){
				Set<IChatListener> clients = chatmap.get(currentChannel);
				clients.remove(chatClient);
			}
		}
	}


	@Override
	public List<String> channelList() {
		List<String> returnList = new ArrayList<>();
		for (Map.Entry<String,Set<IChatListener>> entry : chatmap.entrySet()){
			returnList.add(entry.getKey());
		}
		return returnList;
	}

}
