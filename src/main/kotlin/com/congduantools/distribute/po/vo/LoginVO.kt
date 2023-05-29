package com.congduantools.distribute.po.vo

import com.congduantools.distribute.po.dto.UserDTO
import io.swagger.v3.oas.annotations.media.Schema

/**
 * author： 马世鹏
 *
 * time: 2023/5/19
 *
 * desc:
 */
@Schema(description = "登录返回")
data class LoginVO(
    @field:Schema(description = "用户信息")
    val userInfo: UserDTO,
)
