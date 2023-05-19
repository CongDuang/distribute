package com.congduantools.distribute.controller

import com.congduantools.distribute.common.ResponseInfo
import com.congduantools.distribute.common.ResponseUtils

/**
 * author： 马世鹏
 *
 * time: 2023/5/12
 *
 * desc:
 */
abstract class BaseController {

    protected fun <T> returnData(data: T? = null): ResponseInfo<T> {
        return ResponseUtils.getSuccessfulResult(data)
    }

    protected fun <T> returnMessage(msg: String, data: T): ResponseInfo<T> {
        return ResponseUtils.getSuccessfulResult(msg, data)
    }

    protected fun <T> returnMessage(msg: String): ResponseInfo<T> {
        return ResponseUtils.getSuccessfulResult(msg, null)
    }
}