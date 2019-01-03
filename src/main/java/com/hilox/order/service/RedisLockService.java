package com.hilox.order.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by Hilox on 2019/1/2 0002.
 */
@Component
@Slf4j
public class RedisLockService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 加锁
     * @param key
     * @param value 当前时间+超时时间
     * @return
     */
    public boolean lock(String key, String value) {
        boolean isAbsent = stringRedisTemplate.opsForValue().setIfAbsent(key, value);
        if (isAbsent) {
            return true;
        }

        // 如果锁超时
        String currentValue = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isNotEmpty(currentValue)
                && Long.parseLong(currentValue) < System.currentTimeMillis()) {
            // 获取上一个锁的时间
            String oldValue = stringRedisTemplate.opsForValue().getAndSet(key, value);
            if (StringUtils.isNotEmpty(oldValue) && oldValue.equals(currentValue)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 解锁
     * @param key
     * @param value
     */
    public void unlock(String key, String value) {
        try {
            String currentValue = stringRedisTemplate.opsForValue().get(key);
            if (StringUtils.isNotEmpty(currentValue) && currentValue.equals(value)) {
                stringRedisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            log.error("【redis分布式锁】解锁异常, {}", e);
        }
    }
}
