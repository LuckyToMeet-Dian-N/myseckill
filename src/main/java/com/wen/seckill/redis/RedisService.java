package com.wen.seckill.redis;

import com.wen.seckill.constant.Constants;
import com.wen.seckill.utils.JsonUtils;
import com.wen.seckill.vo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

/**
 * @Description: 这个是主要操作，原谅我的倔强，就是要用原生的jedis操作。
 * 吃了很多调优的大坑。坑的要死。推荐用springboot封装好的redis操作，那个已经封装和调优的很好了。不会出大坑
 * @Author: Gentle
 * @date 2018/9/17  14:02
 */
@Component
public class RedisService {

    private Integer DEFAULT_TIME = -1;


    @Autowired
    private JedisPool jedisPool;


    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } finally {
            jedisClose(jedis);
        }

    }

    /**
     * 设置redis键过期时间
     *
     * @param key
     * @param value
     * @param second
     * @param <T>
     * @return
     */

    public <T> boolean set(String key, T value, int second) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //序列化存入redis
            String str = JsonUtils.ObjectTojson(value);
            if (str == null || str.length() <= 0) {
                return false;
            }
            if (second <= 0) {
                jedis.set(key, str);
            } else {
                jedis.setex(key,second,str);

            }
            return true;
        } finally {
            jedisClose(jedis);
        }
    }


    /**
     * 不设置过期时间
     *
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    public <T> boolean set(String key, T value) {
        return set(key, value, DEFAULT_TIME);
    }

    public boolean del(String key, String field) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.del(key) > 0;
        } finally {
            jedisClose(jedis);
        }
    }


    public <T> boolean hashSet(String key, String field, T value, int second) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //序列化存入redis
            String str = JsonUtils.ObjectTojson(value);
            if (str == null || str.length() <= 0) {
                return false;
            }
            if (second <= 0) {
                jedis.hset(key, field, str);
            } else {
                jedis.setex(key,second,str);
            }
            return true;
        } finally {
            jedisClose(jedis);
        }
    }

    public <T> boolean hashSet(String key, String field, T value) {
        return hashSet(key, field, value, DEFAULT_TIME);
    }


    public String hashGet(String key, String field) {

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();

            return jedis.hget(key, field);
        } finally {
            jedisClose(jedis);
        }
    }

    public boolean hashDel(String key, String field) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();

            return jedis.hdel(key, field) > 0;
        } finally {
            jedisClose(jedis);
        }
    }

    /**
     * 键是否存在
     * @param key
     * @return
     */
    public boolean exists(String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();

            return jedis.exists(key);
        } finally {
            jedisClose(jedis);
        }
    }

    /**
     * hash内的键是否存在
     * @param key
     * @param field
     * @return
     */
    public boolean hashExists(String key,String field){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();

            return jedis.hexists(key,field);
        } finally {
            jedisClose(jedis);
        }
    }

    /**
     * 增加值
     * @param key
     * @return
     */
    public Long incr(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.incr(key);
        } finally {
            jedisClose(jedis);
        }
    }

    /**
     * 减少值
     * @param key
     * @return
     */
    public Long decr(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.decr(key);
        } finally {
            jedisClose(jedis);
        }
    }


    /**
     * 关闭连接
     *
     * @param jedis
     */
    public void jedisClose(Jedis jedis) {
        if (jedis != null) {

            jedis.close();

        }
    }


    public void pipSet(){

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Pipeline pipelined = jedis.pipelined();
            Users users = new Users();

            for (int i = 0; i < 500; i++) {
                users.setUser_id(i);
                pipelined.setex(Constants.USER_SESSION + ":" + i, 600 * 6, JsonUtils.ObjectTojson(users));
            }
            System.out.println(pipelined.syncAndReturnAll());
        }finally {
            jedisClose(jedis);
        }


    }









}
