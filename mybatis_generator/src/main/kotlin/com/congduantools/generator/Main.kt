package com.congduantools.generator

import com.baomidou.mybatisplus.generator.FastAutoGenerator
import com.baomidou.mybatisplus.generator.config.DataSourceConfig
import com.baomidou.mybatisplus.generator.config.OutputFile
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine
import java.sql.Types
import java.util.*

/**
 * author： 马世鹏
 *
 * time: 2023/5/11
 *
 * desc:
 */
fun main() {

    val builder = DataSourceConfig.Builder("jdbc:mysql://localhost:3308/distribute?serverTimezone=GMT%2B8", "root", "123456")

    FastAutoGenerator.create(builder)
        .globalConfig { builder ->
            builder.author("msp")
                .enableKotlin()
                .enableSpringdoc()
                .fileOverride()
                .outputDir("${System.getProperty("user.dir")}/mybatis_generator/src/main/kotlin")
        }
        .dataSourceConfig { a ->
            a.typeConvertHandler { _, typeRegistry, metaInfo ->
                val typeCode = metaInfo.jdbcType.TYPE_CODE
                if (typeCode == Types.SMALLINT) {
                    return@typeConvertHandler DbColumnType.INTEGER
                }
                return@typeConvertHandler typeRegistry.getColumnType(metaInfo)
            }
        }
        .packageConfig { b ->
            b.parent("com.congduantools.generator")
                .moduleName("")
                .pathInfo(
                    Collections.singletonMap(
                        OutputFile.xml,
                        "${System.getProperty("user.dir")}/mybatis_generator/src/main/kotlin"
                    )
                )
        }
        .strategyConfig { c ->
            c.addInclude("user")
                .addTablePrefix("t_", "c_")
        }
        .templateEngine(FreemarkerTemplateEngine())
        .execute()
}