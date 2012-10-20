package de.ingoreschke.jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisDataException;

import de.ingoreschke.redis.IRedis;

public class JedisRedis implements IRedis {
	
	private Jedis jedis;

	public JedisRedis (){
		jedis = new Jedis("localhost");
	    jedis.connect();
	}

	@Override
	public String get(String key) {
		String retval = jedis.get(key);
		return retval;
	}

	@Override
	public String set(String key, String value) {
		String retval = jedis.set(key, value);
		return retval;
	}

	@Override
	public long incr(String key) {
		try{
			long retval =  jedis.incr(key);						
			return retval;
		}catch(JedisDataException e){
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	@Override
	public List<String> mGet(String... keys) {
		List<String> list = jedis.mget(keys);
		return list;
	}

	@Override
	public String mSet(Map<String, String> map) {
		String[] str = new String[map.size()*2];
		int i = 0;
		for (Map.Entry<String, String> entry : map.entrySet()) {
			str[i++] = entry.getKey();
			str[i++] = entry.getValue();
		}
		String retval = jedis.mset(str);
		return retval;
	}

	@Override
	public String hGet(String key, String field) {
		String retval = jedis.hget(key, field);
		return retval;
	}

	@Override
	public int hSet(String key, String field, String value) {
		Long l = jedis.hset(key, field, value);
		int retval = l.intValue();
		return retval;
	}

	@Override
	public List<String> hMGet(String key, String... fields) {
		List<String> list = jedis.hmget(key, fields);
		return list;
	}

	@Override
	public String hMSet(String key, Map<String, String> map) {
		String retval = jedis.hmset(key, map);
		return retval;
	}

	@Override
	public List<String> hKeys(String key) {
		Set<String> keysSet = jedis.hkeys(key);
		List<String> retList = new ArrayList<>();
		for (String str : keysSet) {
			retList.add(str);
		}
		return retList;
	}

	public String flushDB() {
		return jedis.flushDB();
	}
	

}
