package de.ingoreschke.jedis;

import java.util.List;
import java.util.Map;

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
		long retval =  jedis.incr(key);			
		return retval;
	}

	@Override
	public List<String> mGet(String... keys) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String mSet(Map<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String hGet(String key, String field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int hSet(String key, String field, String value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> hMGet(String key, String... fields) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String hMSet(String key, Map<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> hKeys(String key) {
		// TODO Auto-generated method stub
		return null;
	}

}
