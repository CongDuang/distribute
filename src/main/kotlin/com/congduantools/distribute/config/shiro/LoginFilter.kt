package com.congduantools.distribute.config.shiro

import org.apache.shiro.web.filter.AccessControlFilter
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse

/**
 * author： 马世鹏
 *
 *
 * time: 2023/5/19
 *
 *
 * desc:
 */
class LoginFilter : AccessControlFilter() {
    @Throws(Exception::class)
    override fun isAccessAllowed(request: ServletRequest, response: ServletResponse, mappedValue: Any): Boolean {
        return true
    }

    @Throws(Exception::class)
    override fun onAccessDenied(request: ServletRequest, response: ServletResponse): Boolean {
        return true
    }
}
