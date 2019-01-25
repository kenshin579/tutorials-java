/*
 * 
 */
package com.java.examples;

import redis.clients.jedis.Jedis;


final public class RedisActionManager {

    private static final RedisActionManager instance = new RedisActionManager();


    private RedisActionManager() {
    }


    public static RedisActionManager getInstance() {
        return instance;
    }

    public void initEditActionIndex(final String documentId) {
        new RedisContext<Object>().execute(new RedisCommand<Object>() {
            @Override
            public Object execute(Jedis jedis) {
//                jedis.set(getActionIndexStr(documentId), "0");
                return null;
            }
        });
    }

    private interface RedisCommand<T> {

        T execute(Jedis jedis);
    }


    private static final class RedisContext<T> {

        public T execute(RedisCommand<T> command) {
            final Jedis jedis = RedisPoolManager.getInstance().getJedis();
            try {
                return command.execute(jedis);
            } finally {
                RedisPoolManager.getInstance().returnJedis(jedis);
            }
        }
    }
}
