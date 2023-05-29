package com.congduantools.distribute.config.redis

import org.springframework.stereotype.Component

/**
 * author： 马世鹏
 *
 * time: 2023/5/22
 *
 * desc:
 */
@Component
class ClientInfoDao : RedisDao<String>() {

    override fun getKeyPrefix(): String = ""

}