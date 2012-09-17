package de.ingoreschke.redis;

import java.util.HashMap;

public class MockRedis implements IRedis {
	private HashMap<String, String> store;
	
	public MockRedis() {
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
		// TODO Auto-generated method stub
		return null;
	}
	

}
