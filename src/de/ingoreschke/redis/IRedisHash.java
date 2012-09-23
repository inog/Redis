package de.ingoreschke.redis;

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
}
