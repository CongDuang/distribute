package com.congduantools.distribute.controller

import com.congduantools.distribute.common.ResponseInfo
import com.congduantools.distribute.common.validation.GroupSequence
import com.congduantools.distribute.po.dto.BaseSubmitResult
import com.congduantools.distribute.po.dto.RegisterDTO
import com.congduantools.distribute.service.UserService
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
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

    @Resource
    lateinit var userService: UserService

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
    fun register(@RequestBody @Validated(GroupSequence::class) registerDTO: RegisterDTO): ResponseInfo<BaseSubmitResult<Nothing>> {
        return returnData(userService.register(registerDTO))
    }
}