package de.ingoreschke.redis;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RedisString implements IRedisString {
	
private HashMap<String, String> store;
	
	public RedisString() {
		store = new HashMap<>();
	}
	
	@Override
	public String get(String key) {
		return store.get(key);
	}
	
	@Override
	public String set(String key, String value) {
		store.put(key, value);
		return "OK";
	}

	@Override
	public int incr(String key) {
		if(!store.containsKey(key)){
			store.put(key, "0");	
		}
		int i = Integer.valueOf(store.get(key));
		++i;
		store.put(key, Integer.toString(i));
		return i;
	}

	@Override
	public List<String> mGet(String ... keys) {
		List <String> returnList = new LinkedList<>();
		
		for (String key : keys) {
			String val = store.get(key);
			returnList.add(val);
		}
		return returnList;
	}

	@Override
	public String mSet(Map<String, String> map) {
		for(Map.Entry<String, String> entry : map.entrySet()){
			set(entry.getKey(), entry.getValue());
		}
		return "OK";
	}

	
}
