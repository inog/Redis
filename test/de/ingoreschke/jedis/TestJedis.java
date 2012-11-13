package de.ingoreschke.jedis;

import redis.clients.jedis.Jedis;

public class TestJedis {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Jedis jedis = new Jedis("localhost");
	    jedis.connect();
	    jedis.set("foo", "bar");
	    jedis.get("foo");
	}

}
