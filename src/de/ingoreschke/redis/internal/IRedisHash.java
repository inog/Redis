package de.ingoreschke.redis.internal;

import java.util.List;
import java.util.Map;

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
	
	/**
	 * Set multiple hash fields to multiple values
	 * @param key : hold the hashes
	 * @param map : field-value pairs
	 * @return 
	 */
	public String hMSet(String key, Map<String, String> map);
	
	/**
	 *Returns all field names in the hash stored at key
	 * @param key
	 * @return
	 */
	public List<String> hKeys (String key);
}
