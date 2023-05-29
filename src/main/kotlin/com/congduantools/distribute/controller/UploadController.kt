package com.congduantools.distribute.controller

import com.congduantools.distribute.utils.QRCodeUtil
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.io.File

@RestController
@Tag(name = "上传管理")
@RequestMapping("/upload")
class UploadController {

    @PostMapping("/file")
    fun uploadFile(@RequestParam("file") file: MultipartFile): String {
        if (file.isEmpty) {
            return "上传失败，请选择文件"
        }
        val fileName = file.originalFilename
        val filePath = "${System.getProperty("user.dir")}/src/main/resources/static/"
        val dest = File("${filePath}${fileName}")
        try {
            if (!dest.exists()) {
                dest.mkdir()
            }
            file.transferTo(dest)
            return "上传成功"
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return "上传失败"
    }

    @GetMapping("/file")
    fun downloadFile(@RequestParam("fileName") fileName: String): ResponseEntity<Resource> {
        val resource =
            FileSystemResource("${System.getProperty("user.dir")}/src/main/resources/static/${fileName}")
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"${resource.filename}\"")
            .body(resource)
    }

    @GetMapping("/qrcode")
    fun getQRCode(@RequestParam("content") content: String): ResponseEntity<Resource> {
        val file = QRCodeUtil.createCodeToFile(content, null, "qrcode.png")
        val resource = if (file != null) {
            FileSystemResource(file)
        } else {
            null
        }

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"${resource?.filename ?: "unknown"}\"")
            .body(resource)
    }
}