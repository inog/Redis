package de.ingoreschke.redis;

import java.util.List;

public interface IRedisString {
	public String get(String key);
	public String set(String key, Object value);
	public int incr(String key);
	/**
	 * Get a List with all values of given keys
	 * @param keys space separated list of keys (i.e. "key1 key2 key3") 
	 * @return List
	 */
	public List<String> mGet (String ... keys);
	
}
