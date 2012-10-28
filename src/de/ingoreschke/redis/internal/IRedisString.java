package de.ingoreschke.redis.internal;

import java.util.List;
import java.util.Map;

public interface IRedisString {
	String get(String key);
	String set(String key, String value);
	long incr(String key);
	/**
	 * Get a List with all values of given keys
	 * @param keys comma separated list of keystrings (i.e. "key1", "key2", "key3") 
	 * @return List
	 */
	List<String> mGet (String ... keys);
	String mSet(Map<String, String> map);
}
