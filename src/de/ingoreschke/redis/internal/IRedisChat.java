package de.ingoreschke.redis.internal;

public interface IRedisChat {
	/**
	 * send a message to a specific channel
	 * @param channel 
	 * @param message
	 * @return number of registered Clients
	 */
	Integer publish(String channel, String message);
	
	Object subscribe();
	
}
