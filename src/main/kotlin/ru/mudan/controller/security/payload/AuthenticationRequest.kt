package ru.mudan.controller.security.payload

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import lombok.Builder

@Builder
@JvmRecord
@Schema(description = "Запрос на регистрацию, содержащий информацию о новом пользователе")
data class AuthenticationRequest(
    @field:Schema(description = "Никнейм пользователя", example = "ivan_ivanov")
    @field:NotBlank(message = "{login.is_blank}")
    val login: String,
    @field:Schema(description = "Пароль пользователя", example = "securePassword123")
    @field:Size(min = 8, max = 32, message = "{request.password.invalid_size}")
    @field:NotBlank(message = "{password.is_blank}")
    val password: String
)
