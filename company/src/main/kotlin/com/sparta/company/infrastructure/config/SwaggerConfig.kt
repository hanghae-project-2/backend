package com.sparta.company.infrastructure.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import io.swagger.v3.oas.models.servers.Server
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class SwaggerConfig(
    @Value("\${server.port}")
    private val port: Int,
) : WebMvcConfigurer {

    @Bean
    fun openAPI(): OpenAPI {
        val info = Info()
            .title("Company Service")
            .version("V.1.0")
            .description("API 문서")

        val jwtSchemeName = "JWT AUTH"

        val securityRequirement = SecurityRequirement().addList(jwtSchemeName)

        val components = Components()
            .addSecuritySchemes(
                jwtSchemeName,
                SecurityScheme()
                    .name(jwtSchemeName)
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("Bearer")
                    .bearerFormat("JWT")
            )

        return OpenAPI()
            .info(info)
            .components(components)
            .addServersItem(Server().url("/$port"))
            .addSecurityItem(securityRequirement)
    }
}
