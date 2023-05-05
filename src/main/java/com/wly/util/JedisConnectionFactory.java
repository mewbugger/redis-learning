package com.wly.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 无解亦有解
 * @Date: 2023/05/05/8:38
 * @Description:
 */
public class JedisConnectionFactory {

    private static final JedisPool jedisPool;

    static{
        //配置连接
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(8);
        poolConfig.setMaxIdle(8);
        poolConfig.setMinIdle(0);
        poolConfig.setMaxWaitMillis(1000);
        //创建连接池对象
        /**
         *         jedisPool = new JedisPool(poolConfig, "127.0.0.1", 6379, 1000, "123456");
         *         设置密码的版本，此处不设置密码是因为每次在windows启动redis服务都需要设置一次密码。
         */
        jedisPool = new JedisPool(poolConfig, "127.0.0.1", 6379, 1000);
    }

    public static Jedis getJedis()
    {
        return jedisPool.getResource();
    }
}
