package com.congduantools.distribute.common.shiro.realm

import com.congduantools.distribute.config.shiro.ShiroBO
import com.congduantools.distribute.service.UserService
import org.apache.shiro.authc.AuthenticationInfo
import org.apache.shiro.authc.AuthenticationToken
import org.apache.shiro.authc.SimpleAuthenticationInfo
import org.apache.shiro.authc.credential.HashedCredentialsMatcher
import org.apache.shiro.authz.AuthorizationInfo
import org.apache.shiro.realm.AuthorizingRealm
import org.apache.shiro.subject.PrincipalCollection
import org.apache.shiro.util.ByteSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy

/**
 * author： 马世鹏
 *
 * time: 2023/5/16
 *
 * desc:
 */
class UserRealm constructor(private val shiroBO: ShiroBO) : AuthorizingRealm() {

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
                println("${user.password},${username}")
                return SimpleAuthenticationInfo(username, user.password, ByteSource.Util.bytes(shiroBO.salt), name)
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