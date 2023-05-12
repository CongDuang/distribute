package com.congduantools.distribute.common

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody

/**
 * author： 马世鹏
 *
 * time: 2023/5/12
 *
 * desc:
 */
data class ResponseInfo<T>(
    val code: Int,
    val msg: String,
    val data: T? = null
)

