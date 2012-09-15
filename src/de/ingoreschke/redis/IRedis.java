package de.ingoreschke.redis;

interface IRedis {
	public String get(String key);
	public void set(String key, String value);
}
