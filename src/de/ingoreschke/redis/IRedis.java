package de.ingoreschke.redis;

interface IRedis {
	public String get(String key);
	public String set(String key, Object value);
}
