package com.java.examples;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import static org.junit.Assert.assertEquals;

public class JedisTest {

    @Test
    public void simpleTest() {
        RedisPoolManager.getInstance().connect();
        Jedis jedis = RedisPoolManager.getInstance().getJedis();

        jedis.auth("cloudoffice");

        jedis.set("foo", "bar");
        String value = jedis.get("foo");
        assertEquals("bar", value);
    }
}
