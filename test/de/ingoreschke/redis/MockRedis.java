package de.ingoreschke.redis;

public class MockRedis implements IRedis {
	
	@Override
	public String get(String key) {
	
		return null;
	}
	
	@Override
	public String set(String key, Object value) {
		
		return "OK";
	}
	

}
