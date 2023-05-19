package com.congduantools.distribute.po.dto

import com.congduantools.distribute.common.validation.GroupA
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

/**
 * author： 马世鹏
 *
 * time: 2023/5/16
 *
 * desc:
 */
@Schema(name = "LoginDTO", description = "登录DTO")
data class LoginDTO(
    @field:Schema(description = "用户名")
    @field:NotBlank(message = "用户名不能为空", groups = [GroupA::class])
    val username: String?,
    @field:Schema(description = "密码")
    @field:NotBlank(message = "密码不能为空", groups = [GroupA::class])
    val password: String?
)
