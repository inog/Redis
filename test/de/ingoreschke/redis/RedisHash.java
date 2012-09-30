package de.ingoreschke.redis;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RedisHash implements IRedisHash {
	private Map<String, Map<String, String>> store;
	
	public RedisHash(){
		store = new HashMap<>();
	}
	
	@Override
	public String hGet(String key, String field) {
		Map <String, String> hash;
		if (store.containsKey(key)){
			hash = store.get(key);
			return hash.get(field);
		}else {
			return null;
		}
	}

	@Override
	public int hSet(String key, String field, String value) {
		Map<String, String> entry;
		if(!store.containsKey(key)){
			entry = new HashMap<>();
			entry.put(field, value);
			store.put(key, entry);
			return 1;
		} else{
			entry = store.get(key);
			String oldvalue = entry.put(field, value);
			if (oldvalue != null){
				return 0;
			}else{
				return 1;
			}
		}
	}

	@Override
	public List<String> hMGet(String key, String... fields) {
		List <String> returnList = new LinkedList<>();
		Map <String, String> entry = null;
		if (store.containsKey(key)){
			entry = store.get(key);
		}
		
		for (String field : fields){
			if (entry != null){
				returnList.add(entry.get(field));
			}else{
				returnList.add(null);
			}
		}
		return returnList;
	}
}
