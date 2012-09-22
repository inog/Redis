package de.ingoreschke.redis;

public interface IRedisHash {
	public String hGet(String key, String field);
	public String hSet(String key, String field, String value);
}
