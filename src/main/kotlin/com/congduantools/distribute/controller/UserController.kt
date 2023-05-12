package com.congduantools.distribute.controller

import com.congduantools.distribute.common.ResponseInfo
import com.congduantools.distribute.po.dto.RegisterDTO
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

/**
 * author： 马世鹏
 *
 * time: 2023/5/8
 *
 * desc:
 */
@RestController
@RequestMapping("/user")
@Tag(name = "用户管理")
class UserController : BaseController() {

    @RequestMapping("/test", method = [RequestMethod.POST])
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    fun test(@RequestParam str: String): ResponseInfo<Boolean> {
        if (str.isBlank()) {
            throw Exception("0000")
        }
        return returnData()
    }

    @RequestMapping("/register", method = [RequestMethod.POST])
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    fun register(@RequestBody @Valid registerDTO: RegisterDTO): ResponseInfo<Boolean> {
        return returnMessage("注册成功", true)
    }
}