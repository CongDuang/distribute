package com.congduantools.distribute.common

/**
 * author： 马世鹏
 *
 * time: 2023/5/12
 *
 * desc:
 */
object ResponseUtils {

    fun <T> getSuccessfulResult(data: T? = null): ResponseInfo<T> {
        val success = ResponseCodeEnums.SUCCESS
        return ResponseInfo(success.code, success.msg, data)
    }

    fun <T> getSuccessfulResult(msg: String, data: T? = null): ResponseInfo<T> {
        return ResponseInfo(ResponseCodeEnums.SUCCESS.code, msg, data)
    }
}