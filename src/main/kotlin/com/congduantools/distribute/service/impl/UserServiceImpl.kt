package com.congduantools.distribute.service.impl

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.congduantools.distribute.common.exception.InvalidDataException
import com.congduantools.distribute.entity.User
import com.congduantools.distribute.mapper.UserMapper
import com.congduantools.distribute.po.dto.BaseSubmitResult
import com.congduantools.distribute.po.dto.RegisterDTO
import com.congduantools.distribute.service.UserService
import jakarta.annotation.Resource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl : UserService {

    @Resource
    lateinit var userMapper: UserMapper

    override fun register(registerDTO: RegisterDTO): BaseSubmitResult<Nothing> {
        val queryWrapper = KtQueryWrapper(User()).eq(User::username, registerDTO.username)
        var user = userMapper.selectOne(queryWrapper)
        if (user != null) {
            throw InvalidDataException("用户名重复")
        }
        user = User()
        user.username = registerDTO.username
        user.password = registerDTO.password
        if (registerDTO.name.isNullOrBlank()) {
            user.name = registerDTO.username
        } else {
            user.name = registerDTO.name
        }
        val id = userMapper.insert(user)
        return BaseSubmitResult(id)
    }
}