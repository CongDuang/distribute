package com.congduantools.distribute.controller

import com.congduantools.distribute.common.ResponseInfo
import com.congduantools.distribute.common.validation.GroupSequence
import com.congduantools.distribute.po.dto.BaseSubmitResult
import com.congduantools.distribute.po.dto.LoginDTO
import com.congduantools.distribute.po.dto.RegisterDTO
import com.congduantools.distribute.service.UserService
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authc.UsernamePasswordToken
import org.apache.shiro.web.mgt.DefaultWebSecurityManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

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

    @Autowired
    lateinit var securityManager: DefaultWebSecurityManager

    @Autowired
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

    @PostMapping("/login")
    fun login(@RequestBody @Validated(GroupSequence::class) loginDTO: LoginDTO): ResponseInfo<BaseSubmitResult<Nothing>> {
        val subject = SecurityUtils.getSubject()
        val token = UsernamePasswordToken(loginDTO.username, loginDTO.password)
        try {
            subject.login(token)
            return returnMessage("登录成功")
        } catch (e: AuthenticationException) {
            e.printStackTrace()
        }
        return returnMessage("登录失败")
    }
}