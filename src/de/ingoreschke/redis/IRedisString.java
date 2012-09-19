package de.ingoreschke.redis;

public interface IRedisString {
	public String get(String key);
	public String set(String key, Object value);
	public Integer incr(String key);
}
