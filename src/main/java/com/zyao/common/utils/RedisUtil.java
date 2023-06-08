package com.zyao.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @author zyao
 * @version 1.0
 * @date 2023/6/7 13:56
 * @Description Redis工具类
 */
@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    private static final String LOCK_PREFIX = "redis_lock_";
    private static final long LOCK_EXPIRE_TIME = 30000; // 30 seconds
    /**
     * Get value from Redis by key(按键从Redis获取值)
     * @param key Redis key
     * @return Redis value
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 判断key是否存在
     * @param key Redis key
     * @return boolean
     */
    public boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }
    /**
     * Set value to Redis with key and expiration time(使用密钥和过期时间将值设置为Redis)
     * @param key Redis key
     * @param value Redis value
     * @param expireSeconds expiration time in seconds
     */
    public void set(String key, Object value, long expireSeconds) {
        redisTemplate.opsForValue().set(key, value, Duration.ofSeconds(expireSeconds));
    }
    /**
     * Set value to Redis with key and default expiration time of 60 seconds(使用密钥将值设置为Redis,过期时间为60s)
     * @param key Redis key
     * @param value Redis value
     */
    public void set(String key, Object value) {
        set(key, value, 60);
    }
    /**
     * Delete value from Redis by key(通过Key删除缓存)
     * @param key Redis key
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }
    /**
     * Acquire lock with key, return true if lock is acquired successfully, false otherwise
     * 用密钥获取锁，如果成功获取锁则返回true，否则返回false
     * @param key lock key
     * @return true if lock is acquired successfully, false otherwise
     */
    public boolean acquireLock(String key) {
        String lockKey = LOCK_PREFIX + key;
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        Boolean success = valueOperations.setIfAbsent(lockKey, "");
        if (success != null && success) {
//            redisTemplate.boundValueOps(lockKey).expire(LOCK_EXPIRE_TIME,TimeUnit.MINUTES);
            redisTemplate.expire(lockKey,LOCK_EXPIRE_TIME,TimeUnit.MINUTES);
            return true;
        }
        return false;
    }
    /**
     * Release lock with key
     * 用钥匙松开锁
     * @param key lock key
     */
    public void releaseLock(String key) {
        String lockKey = LOCK_PREFIX + key;
        redisTemplate.delete(lockKey);
    }
}
