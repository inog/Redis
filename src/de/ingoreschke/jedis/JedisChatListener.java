package de.ingoreschke.jedis;

import redis.clients.jedis.JedisPubSub;
import de.ingoreschke.redis.internal.IChatListener;

public class JedisChatListener extends JedisPubSub implements IChatListener{
	private String message;


	@Override
	public void onMessage(final String channel, final String message) {
		this.message = message;
		System.out.println(channel + " : " + message);
	}

	@Override
	public void onPMessage(final String pattern, final String channel, final String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSubscribe(final String channel, final int subscribedChannels) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUnsubscribe(final String channel, final int subscribedChannels) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPUnsubscribe(final String pattern, final int subscribedChannels) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPSubscribe(final String pattern, final int subscribedChannels) {
		// TODO Auto-generated method stub

	}

	public String getMessage() {
		return message;
	}
}
