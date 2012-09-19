package de.ingoreschke.redis;


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

	public Integer incr(String key) {
		return redisString.incr(key);
	}
}
