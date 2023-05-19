package com.congduantools.distribute.service.impl

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.congduantools.distribute.common.exception.InvalidDataException
import com.congduantools.distribute.config.shiro.ShiroBO
import com.congduantools.distribute.entity.User
import com.congduantools.distribute.mapper.UserMapper
import com.congduantools.distribute.po.dto.BaseSubmitResult
import com.congduantools.distribute.po.dto.RegisterDTO
import com.congduantools.distribute.service.UserService
import jakarta.annotation.Resource
import org.apache.shiro.crypto.hash.Md5Hash
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl : UserService {

    @Resource
    lateinit var userMapper: UserMapper

    @Autowired
    lateinit var shiroBO: ShiroBO

    override fun register(registerDTO: RegisterDTO): BaseSubmitResult<Nothing> {
        val queryWrapper = KtQueryWrapper(User()).eq(User::username, registerDTO.username)
        var user = userMapper.selectOne(queryWrapper)
        if (user != null) {
            throw InvalidDataException("用户名重复")
        }
        user = User()
        user.username = registerDTO.username
        user.password = Md5Hash(registerDTO.password, shiroBO.salt).toString()
        if (registerDTO.name.isNullOrBlank()) {
            user.name = registerDTO.username
        } else {
            user.name = registerDTO.name
        }
        userMapper.insert(user)
        return BaseSubmitResult(user.id)
    }

    override fun getUserByUsername(username: String): User? {
        val queryWrapper = KtQueryWrapper(User()).eq(User::username, username)
        return userMapper.selectOne(queryWrapper)
    }
}