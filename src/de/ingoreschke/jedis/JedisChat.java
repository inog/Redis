package de.ingoreschke.jedis;

import java.util.List;

import redis.clients.jedis.Jedis;
import de.ingoreschke.redis.internal.IChatListener;
import de.ingoreschke.redis.internal.IRedisChat;

public class JedisChat implements IRedisChat {

	private Jedis jedis;

	public JedisChat() {
		jedis = new Jedis("localhost");
		jedis.connect();
	}

	@Override
	public int publish(final String channel, final String message) {
		jedis.publish(channel, message);
		return 0;
	}

	@Override
	public void subscribe(final IChatListener chatClient, final String... channels) {
	}

	@Override
	public void unsubscribe(final IChatListener chatClient, final String... channels) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> channelList() {
		// TODO Auto-generated method stub
		return null;
	}

}
