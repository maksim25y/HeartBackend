package ru.mudan.controller.security.payload

import io.swagger.v3.oas.annotations.media.Schema

@JvmRecord
@Schema(description = "Ответ аутентификации, содержащий JWT токен")
data class AuthenticationResponse(
    @Schema(
        description = "JWT токен для аутентифицированного пользователя",
        example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
    )
    val token: String
)
