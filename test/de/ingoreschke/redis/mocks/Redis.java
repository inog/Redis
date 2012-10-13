package de.ingoreschke.redis.mocks;

import java.util.List;
import java.util.Map;

import de.ingoreschke.redis.IRedis;
import de.ingoreschke.redis.internal.IRedisHash;
import de.ingoreschke.redis.internal.IRedisString;


public class Redis implements IRedis {
	

	IRedisString redisString;
	IRedisHash redisHash;

	public Redis(){
		redisString = new RedisString();
		redisHash = new RedisHash();
	}
	
	public String get(String key) {
		return redisString.get(key);
	}

	public String set(String key, String value) {
		return redisString.set(key, value);
	}

	public long incr(String key) {
		return redisString.incr(key);
	}

	public List<String> mGet(String... keys) {
		return redisString.mGet(keys);
	}

	public String mSet(Map<String, String> map) {
		return redisString.mSet(map);
	}

	public String hGet(String key, String field) {
		return redisHash.hGet(key, field);
	}
	
	public int hSet(String key, String field, String value) {
		return redisHash.hSet(key, field, value);
	}

	public List<String> hMGet(String key, String... fields) {
		return redisHash.hMGet(key, fields);
	}

	public String hMSet(String key, Map<String, String> map) {
		return redisHash.hMSet(key, map);
	}

	public List<String> hKeys(String key) {
		return redisHash.hKeys(key);
	}
	
	
}
