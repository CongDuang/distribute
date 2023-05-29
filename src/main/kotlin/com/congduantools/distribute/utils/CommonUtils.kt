package com.congduantools.distribute.utils

import org.springframework.beans.BeanUtils

/**
 * author： 马世鹏
 *
 * time: 2023/5/26
 *
 * desc:
 */
object CommonUtils {

    fun <S, T> copy(source: S?, targetType: Class<T>): T? {
        if (source == null) {
            return null
        }
        try {
            val target = targetType.newInstance()
            BeanUtils.copyProperties(source, target as Any)
            return target
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

}