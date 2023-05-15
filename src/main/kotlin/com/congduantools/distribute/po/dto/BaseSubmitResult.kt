package com.congduantools.distribute.po.dto

import com.fasterxml.jackson.annotation.JsonInclude
import io.swagger.v3.oas.annotations.media.Schema


/**
 * author： 马世鹏
 *
 * time: 2023/5/15
 *
 * desc:
 */
data class BaseSubmitResult<T>(
    @field:Schema(description = "id")
    @field:JsonInclude(JsonInclude.Include.NON_NULL)
    val id: Int? = null,
    @field:Schema(description = "其他")
    @field:JsonInclude(JsonInclude.Include.NON_NULL)
    val other: T? = null
)