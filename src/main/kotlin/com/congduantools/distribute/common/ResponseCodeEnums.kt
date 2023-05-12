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
    FAIL(500, "failed"),
    VALIDATION_FAILED(501, "validation failed")
}