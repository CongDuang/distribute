package com.congduantools.distribute.config.shiro.cache

import org.apache.shiro.cache.AbstractCacheManager
import org.apache.shiro.cache.Cache
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy

/**
 * author： 马世鹏
 *
 * time: 2023/5/26
 *
 * desc:
 */
class RedisCacheManager : AbstractCacheManager() {

    @Autowired
    @Lazy
    private lateinit var redisCache: RedisCache<Any, Any>

    override fun createCache(name: String?): Cache<*, *> {
        return redisCache
    }
}