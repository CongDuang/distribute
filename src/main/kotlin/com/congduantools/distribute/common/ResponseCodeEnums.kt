package com.congduantools.distribute.common

/**
 * author： 马世鹏
 *
 * time: 2023/5/12
 *
 * desc:
 */
enum class ResponseCodeEnums(val code: Int, val msg: String) {

    SUCCESS(200, "success"),
    VALIDATION_FAILED(201, "validation failed"),
    SUCCESS_BUT_ERROR(201, "request success but has other error"),
    FAIL(500, "failed"),
}