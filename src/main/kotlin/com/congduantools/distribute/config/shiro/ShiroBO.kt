package com.congduantools.distribute.config.shiro

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
 * author： 马世鹏
 *
 * time: 2023/5/19
 *
 * desc:
 */
@Component
@ConfigurationProperties(prefix = "shiro.config")
data class ShiroBO(
    var hashAlgorithmName: String = "md5",
    var hashIterations: Int = 1,
    var salt: String = "",
    var shiroSessionPrefix: String = "session",
    var shiroSessionValidationInterval: Int = 60000,
    var shiroSessionTimeout: Int = 1800000,
    var shiroCachePrefix: String = "shiro-cache",
    var shiroCacheExpireTime: Int = 120000,
    var isLogInSeparately: Boolean = true,
) {


}