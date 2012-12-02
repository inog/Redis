package de.ingoreschke.redis.internal;

public interface IChatListener {
	void onMessage(String channel, String message);
}
