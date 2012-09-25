package de.ingoreschke.redis;

import java.util.List;

public interface IRedisHash {
	public String hGet(String key, String field);
	
	/**
	 * 
	 * @param key
	 * @param field
	 * @param value
	 * @return  1 if field is a new field in the hash and value was set. <br>
	 * 			0 if field already exists in the hash and the value was updated.	
	 */
	public int hSet(String key, String field, String value);
	
	/**
	 * Returns the values associated with the specified fields in the hash stored at key.
	 * @param key
	 * @param field
	 * @return
	 */
	public List<String> hMGet(String key, String...fields);
}
