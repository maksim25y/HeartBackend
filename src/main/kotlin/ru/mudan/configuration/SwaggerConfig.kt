package ru.mudan.configuration

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.security.SecurityScheme

@OpenAPIDefinition(
    info = Info(
        title = "Проект KubSTU Kotlin API",
        description = "Бэкенд для проекта по сердцу КубГТУ",
        version = "1.0.0",
        contact = Contact(name = "GitHub", url = "https://github.com/maksim25y/HeartBackend")
    )
)
@SecurityScheme(name = "JWT", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
class SwaggerConfig
