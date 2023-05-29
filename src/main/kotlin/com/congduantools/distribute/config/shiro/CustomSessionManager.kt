package com.congduantools.distribute.config.shiro

import org.apache.shiro.session.ExpiredSessionException
import org.apache.shiro.session.Session
import org.apache.shiro.session.mgt.SessionKey
import org.apache.shiro.web.servlet.ShiroHttpServletRequest
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager
import org.apache.shiro.web.session.mgt.WebSessionKey
import org.apache.shiro.web.session.mgt.WebSessionManager
import org.apache.shiro.web.util.WebUtils
import java.io.Serializable
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse

/**
 * author： 马世鹏
 *
 * time: 2023/5/22
 *
 * desc:
 */
class CustomSessionManager : DefaultWebSessionManager(), WebSessionManager {

    companion object {
        private const val ACCESS_TOKEN_HEADER_KEY = "Authorization"
    }

    override fun getSessionId(request: ServletRequest?, response: ServletResponse?): Serializable {
        val headerValue = WebUtils.toHttp(request).getHeader(ACCESS_TOKEN_HEADER_KEY)

        return if (!headerValue.isNullOrBlank()) {
            request?.apply {
                setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, "Stateless request")
                setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, headerValue)
                setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, java.lang.Boolean.TRUE)
            }
            headerValue
        } else {
            // 否则按默认规则从cookie取sessionId
            super.getSessionId(request, response)
        }
    }

    override fun retrieveSession(sessionKey: SessionKey?): Session {
        val sessionId = getSessionId(sessionKey)
        val request: ServletRequest?
        if (sessionKey is WebSessionKey) {
            request = sessionKey.servletRequest
        } else {
            request = null
        }

        if (null != request && null != sessionId) {
            val session = request.getAttribute(sessionId.toString()) as Session?
            if (session != null) {
                return session
            }
        }

        val session = super.retrieveSession(sessionKey)
        if (null != request && null != sessionId) {
            request.setAttribute(sessionId.toString(), session)
        }
        return session
    }

    override fun onExpiration(s: Session?, ese: ExpiredSessionException?, key: SessionKey?) {
        super.onExpiration(s, ese, key)

    }
}