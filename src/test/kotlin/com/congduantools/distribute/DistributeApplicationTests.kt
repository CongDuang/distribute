package com.congduantools.distribute

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.StringRedisTemplate

@SpringBootTest
class DistributeApplicationTests {

    @Autowired
    private lateinit var stringRedisTemplate: StringRedisTemplate

    @Test
    fun contextLoads() {
    }

    @Test
    fun testStringRedisSetKey() {
        stringRedisTemplate.opsForValue().set("username", "mashipeng1")
    }
}
