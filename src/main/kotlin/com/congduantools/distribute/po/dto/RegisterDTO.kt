package com.congduantools.distribute.po.dto

import com.congduantools.distribute.common.validation.GroupA
import com.congduantools.distribute.common.validation.GroupB
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

/**
 * author： 马世鹏
 *
 * time: 2023/5/11
 *
 * desc:
 */
@Schema(name = "RegisterDTO", description = "注册DTO")
data class RegisterDTO(
    @field:Schema(description = "用户名")
    @field:NotBlank(message = "用户名不能为空", groups = [GroupA::class])
    var username: String,

    @field:Schema(description = "密码")
    @field:NotBlank(message = "密码不能为空", groups = [GroupB::class])
    var password: String,

    @field:Schema(description = "姓名")
    var name: String? = username,
)