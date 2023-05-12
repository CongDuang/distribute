package com.congduantools.distribute.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * author： 马世鹏
 *
 * time: 2023/5/8
 *
 * desc:
 */
@Configuration
class SwaggerConfig {

    @Bean
    fun httpApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("http")
            .pathsToMatch("/**")
            .build()
    }

    @Bean
    fun apiInfo(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("我的内测分发平台")
                    .version("1.0.0")
            )
    }
}