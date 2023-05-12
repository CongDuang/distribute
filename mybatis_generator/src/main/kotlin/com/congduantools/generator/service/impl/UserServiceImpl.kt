package com.congduantools.generator.service.impl;

import com.congduantools.generator.entity.User;
import com.congduantools.generator.mapper.UserMapper;
import com.congduantools.generator.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author msp
 * @since 2023-05-11
 */
@Service
open class UserServiceImpl : ServiceImpl<UserMapper, User>(), IUserService {

}
