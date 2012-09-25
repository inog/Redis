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
		Map<String, String> hash;
		if(!store.containsKey(key)){
			hash = new HashMap<>();
			hash.put(field, value);
			store.put(key, hash);
			return 1;
		} else{
			hash = store.get(key);
			if (hash.containsKey(field)){
				hash.remove(field);
				hash.put(field, value);
				return 0;
			}else{
				hash.put(field, value);
				return 1;
			}
		}
	}

	@Override
	public List<String> hMGet(String key, String... fields) {
		List <String> returnList = new LinkedList<>();
		Map <String, String> hash = store.get(key);
			
		for (String field : fields){
			if (hash != null){
				returnList.add(hash.get(field));
			}else{
				returnList.add(null);
			}
		}
		return returnList;
	}
}
