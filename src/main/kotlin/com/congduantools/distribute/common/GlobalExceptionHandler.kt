package com.congduantools.distribute.common

import com.congduantools.distribute.common.exception.InvalidDataException
import org.slf4j.LoggerFactory
import org.springframework.validation.BindException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import kotlin.math.log

/**
 * author： 马世鹏
 *
 * time: 2023/5/12
 *
 * desc:
 */
@RestControllerAdvice
class GlobalExceptionHandler {

    companion object {
        private val logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)
    }

    @ExceptionHandler(Exception::class)
    fun handle(e: Exception): ResponseInfo<Nothing> {
        logger.error(e.message, e)
        return when (e) {
            is BindException -> {
                val first = e.bindingResult.allErrors.first()
                ResponseInfo(ResponseCodeEnums.VALIDATION_FAILED.code, first.defaultMessage ?: ResponseCodeEnums.VALIDATION_FAILED.msg)
            }

            is InvalidDataException -> {
                ResponseInfo(ResponseCodeEnums.VALIDATION_FAILED.code, e.message ?: ResponseCodeEnums.VALIDATION_FAILED.msg)
            }

            else -> {
                ResponseInfo(ResponseCodeEnums.FAIL.code, e.toString())
            }
        }
    }
}