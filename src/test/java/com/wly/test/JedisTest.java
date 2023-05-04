package com.wly.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 无解亦有解
 * @Date: 2023/05/04/23:42
 * @Description:
 */
public class JedisTest {

    //引入依赖
    private Jedis jedis;;

    @BeforeEach
    void setUp() {
        //1.建立连接
        /**
         * 无法设置ip为自定义的地址，例如：jedis = new Jedis("192.168.150.101", 6379)
         * 原因：windows下还未找到修改绑定ip的方法，只能用本地地址访问
         * 用上述代码，会报错：redis.clients.jedis.exceptions.JedisConnectionException: Failed to create socket.
         */
        jedis = new Jedis("127.0.0.1", 6379);
        //2.设置密码
        /**
         * 当windows下的redis没有设置密码，如果这里设置密码就报错
         * redis.clients.jedis.exceptions.JedisDataException: ERR Client sent AUTH, but no password is set
         *
         */
        jedis.auth("123456");
        ///3.选择库
        jedis.select(0);
    }

    @Test
    void testString(){
       //存入数据
        String result = jedis.set("name", "王乐岩");
        System.out.println("result = " + result);
        //获取数据
        String name = jedis.get("name");
        System.out.println("name = " + name);

    }

    @Test
    void testHash(){
        //插入hash数据
        jedis.hset("user:1", "name", "WLY");
        jedis.hset("user:1", "age", "22");

        //获取
        Map<String, String> map = jedis.hgetAll("user:1");
        System.out.println(map);
    }

    @AfterEach
    void tearDown() {
        //释放连接
        if (jedis != null){
            jedis.close();
        }
    }
}
