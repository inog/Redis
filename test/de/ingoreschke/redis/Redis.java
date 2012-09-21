package de.ingoreschke.redis;

import java.util.List;


public class Redis implements IRedis {
	IRedisString redisString;

	public Redis(){
		redisString = new RedisString();
	}
	
	public String get(String key) {
		return redisString.get(key);
	}

	public String set(String key, Object value) {
		return redisString.set(key, value);
	}

	public int incr(String key) {
		return redisString.incr(key);
	}

	public List<String> mGet(String keys) {
		return redisString.mGet(keys);
	}
}
