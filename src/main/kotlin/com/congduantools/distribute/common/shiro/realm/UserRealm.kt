package com.congduantools.distribute.common.shiro.realm

import com.congduantools.distribute.service.UserService
import jakarta.annotation.Resource
import org.apache.shiro.authc.AuthenticationInfo
import org.apache.shiro.authc.AuthenticationToken
import org.apache.shiro.authc.SimpleAuthenticationInfo
import org.apache.shiro.authc.UsernamePasswordToken
import org.apache.shiro.authz.AuthorizationInfo
import org.apache.shiro.realm.AuthorizingRealm
import org.apache.shiro.subject.PrincipalCollection
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy

/**
 * author： 马世鹏
 *
 * time: 2023/5/16
 *
 * desc:
 */
class UserRealm : AuthorizingRealm() {

    @Lazy
    @Autowired
    lateinit var userService: UserService

    /**
     * 认证
     */
    override fun doGetAuthenticationInfo(token: AuthenticationToken?): AuthenticationInfo? {
        val username = token?.principal?.toString()
        if (username != null) {
            val user = userService.getUserByUsername(username)
            if (user != null) {
                return SimpleAuthenticationInfo(user.username, user.password, token.principal.toString())
            }
        }
        return null
    }

    /**
     * 授权
     */
    override fun doGetAuthorizationInfo(principals: PrincipalCollection?): AuthorizationInfo? {
        return null
    }
}