package com.congduantools.distribute.config.shiro.cache

import com.congduantools.distribute.config.shiro.ShiroBO
import jakarta.annotation.Resource
import org.apache.shiro.cache.Cache
import org.springframework.context.annotation.Lazy
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

/**
 * author： 马世鹏
 *
 * time: 2023/5/26
 *
 * desc:
 */
@Component
class RedisCache<K : Any, V> : Cache<K, V> {

    @Resource
    @Lazy
    lateinit var shiroBO: ShiroBO

    @Resource
    @Lazy
    private lateinit var redisTemplate: RedisTemplate<Any, V>

    private fun getKey(key: Any?): String {
        return "${shiroBO.shiroCachePrefix}:${key ?: ""}"
    }

    override fun get(key: K?): V? {
        return redisTemplate.opsForValue().get(getKey(key))
    }

    override fun put(key: K?, value: V): V {
        redisTemplate.opsForValue().set(getKey(key), value, shiroBO.shiroCacheExpireTime.toLong(), TimeUnit.MILLISECONDS)
        return value
    }

    override fun remove(key: K?): V? {
        val value = redisTemplate.opsForValue().get(getKey(key))
        redisTemplate.opsForValue().operations.delete(getKey(key))
        return value
    }

    override fun clear() {

    }

    override fun size(): Int {
        return redisTemplate.keys("${shiroBO.shiroCachePrefix}:*").size
    }

    override fun keys(): MutableSet<K> {
        return redisTemplate.keys("${shiroBO.shiroCachePrefix}:*")
            .map { i ->
                i.toString().replace("${shiroBO.shiroCachePrefix}:", "")
                i as K
            }
            .toMutableSet()
    }

    override fun values(): MutableCollection<V>? {
        return null
    }
}