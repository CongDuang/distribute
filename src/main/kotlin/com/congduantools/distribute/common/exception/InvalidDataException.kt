package com.congduantools.distribute.common.exception

import org.springframework.validation.BindingResult

/**
 * author： 马世鹏
 *
 * time: 2023/5/15
 *
 * desc:
 */
class InvalidDataException : RuntimeException {

    constructor() : super()
    constructor(message: String?) : super(message)
    constructor(cause: Throwable?) : super(cause)
    constructor(bindingResult: BindingResult) : super(bindingResult.fieldError?.defaultMessage ?: "")
}