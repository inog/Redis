package de.ingoreschke.redis;

import java.util.HashMap;

public class RedisString implements IRedisString {
	
private HashMap<String, String> store;
	
	public RedisString() {
		store = new HashMap<String, String>();
	}
	
	@Override
	public String get(String key) {
		return store.get(key);
	}
	
	@Override
	public String set(String key, Object value) {
		store.put(key, value.toString());
		return "OK";
	}

	@Override
	public Integer incr(String key) {
		if(!store.containsKey(key)){
			store.put(key, "0");	
		}
		int i = Integer.valueOf(store.get(key));
		++i;
		store.put(key, Integer.toString(i));
		return i;
	}
}
