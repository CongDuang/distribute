package com.congduantools.distribute.service

import com.congduantools.distribute.entity.User
import com.congduantools.distribute.po.dto.BaseSubmitResult
import com.congduantools.distribute.po.dto.RegisterDTO

/**
 * author： 马世鹏
 *
 * time: 2023/5/15
 *
 * desc: 用户服务
 */
interface UserService {
    /**
     * 注册
     */
    fun register(registerDTO: RegisterDTO): BaseSubmitResult<Nothing>

    fun getUserByUsername(username: String): User?
}