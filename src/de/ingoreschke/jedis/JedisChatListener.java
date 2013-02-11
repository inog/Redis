package de.ingoreschke.jedis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import redis.clients.jedis.JedisPubSub;
import de.ingoreschke.redis.internal.IChatListener;

public class JedisChatListener extends JedisPubSub implements IChatListener{
	private Map<String, List<String>> messages;

	public JedisChatListener(){
		this.messages = new HashMap<String, List<String>>();
	}

	@Override
	public void onMessage(final String channel, final String message) {
		appendMessage(channel, message);
	}


	private void appendMessage(final String channel, final String message) {
		boolean channelExists = messages.containsKey(channel);
		if(channelExists){
			messages.get(channel).add(message);
		}else{
			ArrayList<String> messageList = new ArrayList<>();
			messageList.add(message);
			messages.put(channel, messageList);
		}
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



	/**
	 * retrieve the last message in the given channel
	 * @return the messages
	 */
	@Override
	public String getLastMessage(final String channel) {
		if(!messages.containsKey(channel)){
			return "";
		}
		List<String> msgs = messages.get(channel);
		if(msgs.isEmpty()){
			return "";
		}
		String lastmessage = msgs.get(msgs.size() -1);
		return StringUtils.trimToEmpty(lastmessage);
	}

	/**
	 * retrieve all messages in the given channel
	 * @return a List of messages
	 */
	@Override
	public List<String> getAllMessages(final String channel) {
		return messages.get(channel);
	}


}
