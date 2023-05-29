package com.congduantools.distribute.config.shiro

import jakarta.annotation.Resource
import org.apache.shiro.session.Session
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO
import org.apache.shiro.util.CollectionUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.io.Serializable
import java.util.concurrent.TimeUnit

/**
 * author： 马世鹏
 *
 * time: 2023/5/23
 *
 * desc:
 */
class RedisSessionDao : AbstractSessionDAO() {

    @Resource
    @Lazy
    private lateinit var redisTemplate: RedisTemplate<String, Session>

    @Resource
    @Lazy
    lateinit var shiroBO: ShiroBO

    private fun saveSession(session: Session?) {
        session?.let { s ->
            session.id?.let { id ->
                val key = getKey(id.toString())
                redisTemplate.opsForValue()
                    .set(key, s, (shiroBO.shiroSessionTimeout + shiroBO.shiroSessionValidationInterval).toLong(), TimeUnit.MICROSECONDS)
            }
        }
    }

    private fun getKey(key: String) = "${shiroBO.shiroSessionPrefix}:${key}"

    override fun doCreate(session: Session?): Serializable {
        val sessionId = generateSessionId(session)
        // 绑定session 和 sessionId
        assignSessionId(session, sessionId)
        saveSession(session)
        return sessionId
    }

    override fun doReadSession(sessionId: Serializable?): Session? {
        if (sessionId == null) {
            return null
        }
        val key = getKey(sessionId.toString())
        return redisTemplate.opsForValue().get(key)
    }

    override fun update(session: Session?) {
        saveSession(session)
    }

    override fun delete(session: Session?) {
        session?.let { s ->
            s.id?.let { id ->
                val key = getKey(id.toString())
                redisTemplate.delete(key)
            }
        }
    }

    override fun getActiveSessions(): MutableCollection<Session> {
        val keys = redisTemplate.keys(shiroBO.shiroSessionPrefix + ":*")
        val sessions = hashSetOf<Session>()
        if (keys.isEmpty()) {
            return sessions
        }
        keys.forEach {
            val session = redisTemplate.opsForValue().get(it)
            session?.apply {
                sessions.add(this)
            }
        }
        return sessions
    }
}