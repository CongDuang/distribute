package com.congduantools.distribute.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.congduantools.distribute.entity.User
import org.apache.ibatis.annotations.Mapper

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author msp
 * @since 2023-05-11
 */
@Mapper
interface UserMapper : BaseMapper<User>
