package com.congduantools.generator.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import java.io.Serializable
import java.time.LocalDateTime
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>
 * 
 * </p>
 *
 * @author msp
 * @since 2023-05-11
 */
@Schema(name = "User", description = "$!{table.comment}")
class User : Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    var id: Int? = null

    @Schema(description = "用户名")
    var username: String? = null

    @Schema(description = "密码")
    var password: String? = null

    @Schema(description = "名字")
    var name: String? = null

    @Schema(description = "邮箱")
    var email: String? = null

    @Schema(description = "手机号")
    var mobile: Int? = null

    @Schema(description = "创建时间")
    var createDateTime: LocalDateTime? = null

    var updateDateTime: LocalDateTime? = null

    var apiKey: String? = null

    var userKey: String? = null

    @Schema(description = "是否开启发送邮件通知：1开启")
    var enableEmailNotify: Boolean? = null

    @Schema(description = "是否开启上传：1开启")
    var enableUpload: Boolean? = null

    @Schema(description = "群组id")
    var groupId: Int? = null

    override fun toString(): String {
        return "User{" +
        "id=" + id +
        ", username=" + username +
        ", password=" + password +
        ", name=" + name +
        ", email=" + email +
        ", mobile=" + mobile +
        ", createDateTime=" + createDateTime +
        ", updateDateTime=" + updateDateTime +
        ", apiKey=" + apiKey +
        ", userKey=" + userKey +
        ", enableEmailNotify=" + enableEmailNotify +
        ", enableUpload=" + enableUpload +
        ", groupId=" + groupId +
        "}"
    }
}
