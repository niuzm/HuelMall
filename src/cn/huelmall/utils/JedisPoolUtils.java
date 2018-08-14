package cn.huelmall.utils;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtils {
	private static JedisPool pool = null;

	static {
		// 加载配置文件
		InputStream is = JedisPoolUtils.class.getClassLoader().getResourceAsStream("redis.properties");
		Properties pro = new Properties();
		try {
			pro.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 获得池子对象
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		// 最大闲置个数
		poolConfig.setMaxIdle(Integer.parseInt(pro.get("redis.maxIdle").toString()));
		// 最大连接数
		poolConfig.setMaxTotal(Integer.parseInt(pro.get("redis.maxTotal").toString()));
		pool = new JedisPool(poolConfig, pro.getProperty("redis.url"),
				Integer.parseInt(pro.get("redis.port").toString()));
	}

	// 获得jedis资源的方法
	public static Jedis getJedis() {
		return pool.getResource();
	}

	public static void main(String[] args) {
		Jedis jedis = getJedis();
		System.out.println(jedis.get("username"));
	}
}
