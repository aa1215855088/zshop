package com.hnguigu.zshop.common.util;

import com.hnguigu.zshop.common.constant.RedisConstant;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.crypto.hash.Hash;
import redis.clients.jedis.JedisPool;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.util.SafeEncoder;

import java.util.List;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-09-08 08:32
 **/
public class RedisUtil {
    private static JedisPool jedisPool = null;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(50);
        config.setMaxIdle(5);
        config.setMaxWaitMillis(200000);
        config.setTestOnBorrow(true);
        jedisPool = new JedisPool(config, RedisConstant.HOST, RedisConstant.PORT, 200000);
    }


    /**
     * 获取Jedis实例
     *
     * @return
     */
    public synchronized static Jedis getResource() {
        try {
            if (jedisPool != null) {
                Jedis resource = jedisPool.getResource();
                return resource;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 释放jedis资源
     *
     * @param jedis
     */
    public static void returnResource(final Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnResource(jedis);
        }
    }


    /**
     * 存在某个Key
     */
    public static Boolean existsObject(String key) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.exists(key.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 设置字符串结果
     */
    public static boolean set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            String result = jedis.set(key, value);
            if ("OK".equals(result)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }
        return false;
    }

    public static void del(String key) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Long result = jedis.del(key);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }
    }


    /**
     * 设置字符串结果带过期时间
     */
    public static boolean set(String key, String value, int expiretime) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            String result = jedis.setex(key, expiretime, value);
            if ("OK".equals(result)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }
        return false;
    }

    /**
     * 获得字符串结果
     */
    public static String get(String key) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            String result = jedis.get(key);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }
        return null;
    }

}
