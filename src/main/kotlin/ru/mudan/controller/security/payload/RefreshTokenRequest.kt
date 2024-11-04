package ru.mudan.controller.security.payload

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Запрос на обновление jwt токена, содержащий в себе refresh токен")
data class RefreshTokenRequest(
    val token: String
)