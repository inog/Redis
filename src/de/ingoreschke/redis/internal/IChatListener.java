package de.ingoreschke.redis.internal;

import java.util.List;

public interface IChatListener {
	void onMessage(String channel, String message);
	String getLastMessage(String channel);
	List<String> getAllMessages(String channel);
}


