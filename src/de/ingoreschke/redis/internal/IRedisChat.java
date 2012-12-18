package de.ingoreschke.redis.internal;

import java.util.List;

public interface IRedisChat {
	/**
	 * send a message to a specific channel
	 * @param channel
	 * @param message
	 * @return number of registered Clients
	 */
	int publish(String channel, String message);
	void subscribe(IChatListener chatClient, String ... channels);
	void unsubscribe(IChatListener chatClient, String ... channels);
	List<String> channelList();
}
