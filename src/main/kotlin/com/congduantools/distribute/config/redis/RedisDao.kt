package com.congduantools.distribute.config.redis

import jakarta.annotation.Resource
import org.apache.shiro.cache.CacheException
import org.springframework.data.redis.core.RedisTemplate
import java.util.stream.Collectors

/**
 * author： 马世鹏
 *
 * time: 2023/5/22
 *
 * desc:
 */
abstract class RedisDao<T> {

    @Resource
    private lateinit var redisTemplate: RedisTemplate<Any, T>

    /**
     * 获取key前缀
     */
    protected abstract fun getKeyPrefix(): String

    private fun getKey(key: Any): String = "${getKeyPrefix()}:$key"

    @Throws(CacheException::class)
    fun put(key: Int, value: T): T {
        redisTemplate.opsForValue().get(getKey(key))
        return value
    }

    @Throws(CacheException::class)
    fun get(key: Any): T? {
        return redisTemplate.opsForValue().get(getKey(key))
    }

    @Throws(CacheException::class)
    fun remove(key: Any): T? {
        val v = redisTemplate.opsForValue().get(getKey(key))
        redisTemplate.opsForValue().operations.delete(getKey(key))
        return v
    }

    fun size() = redisTemplate.keys("${getKeyPrefix()}:*").size

    fun keys(): MutableSet<Any> = redisTemplate.keys("${getKeyPrefix()}:*")
        .stream()
        .map { i -> i.toString().replace("${getKeyPrefix()}:", "") }
        .collect(Collectors.toSet())
}