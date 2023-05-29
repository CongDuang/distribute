package com.congduantools.distribute.service

import com.congduantools.distribute.entity.User
import com.congduantools.distribute.po.dto.BaseSubmitResult
import com.congduantools.distribute.po.dto.RegisterDTO
import com.congduantools.distribute.po.dto.UserDTO

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

    /**
     * 根据[username]获取用户
     */
    fun getUserByUsername(username: String): UserDTO?

    /**
     * 更新用户最后一次的等偶商检
     */
    fun updateLastLoginTime(userId: Int): BaseSubmitResult<Nothing>


}