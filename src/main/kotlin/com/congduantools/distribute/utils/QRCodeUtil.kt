package com.congduantools.distribute.utils

import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import org.slf4j.LoggerFactory
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

object QRCodeUtil {

    private val logger = LoggerFactory.getLogger(QRCodeUtil::class.java)
    private const val CODE_WIDTH = 400
    private const val CODE_HEIGHT = 400
    private const val FRONT_COLOR = 0x000000
    private const val BG_COLOR = 0xffffff

    fun createCodeToFile(content: String, codeImgFileSaveDir: File?, filename: String): File? {
        var codeDir = codeImgFileSaveDir
        try {
            if (content.isBlank() || filename.isBlank()) {
                return null;
            }
            if (codeDir == null || codeDir.isFile) {
                codeDir = File("${System.getProperty("user.dir")}/src/main/resources/static/code")
            }
            if (!codeDir.exists()) {
                codeDir.mkdirs()
            }
            val bufferedImage = getBufferedImage(content)

            val codeImageFile = File(codeDir, filename)
            ImageIO.write(bufferedImage, "png", codeImageFile)
            logger.info("二维码生成成功：" + codeImageFile.path)
            return codeImageFile
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    @Throws(WriterException::class)
    fun getBufferedImage(content: String): BufferedImage {
        val hints: MutableMap<EncodeHintType, Any> = mutableMapOf()

        hints[EncodeHintType.CHARACTER_SET] = "UTF-8"

        hints[EncodeHintType.ERROR_CORRECTION] = ErrorCorrectionLevel.M

        hints[EncodeHintType.MARGIN] = 1

        val multiFormatWriter = MultiFormatWriter()
        val bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, CODE_WIDTH, CODE_HEIGHT, hints)
        val bufferedImage = BufferedImage(CODE_WIDTH, CODE_HEIGHT, BufferedImage.TYPE_INT_BGR)
        for (x in 0 until CODE_WIDTH) {
            for (y in 0 until CODE_HEIGHT) {
                bufferedImage.setRGB(x, y, if (bitMatrix.get(x, y)) FRONT_COLOR else BG_COLOR)
            }
        }
        return bufferedImage
    }
}