package ru.mudan.controller.security.payload

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class RegisterRequest(
    @field:Schema(description = "Никнейм пользователя", example = "ivan_ivanov")
    @field:NotBlank(message = "{login.is_blank}")
    val login: String,
    @field:Schema(name = "display_name", example = "Иванов Иван Иванович")
    @field:NotBlank(message = "{register.request.display_name.is_blank}")
    @field:JsonProperty("display_name") @param:JsonProperty("display_name") val displayName: String,
    @field:Schema(description = "Пароль пользователя", example = "securePassword123")
    @field:Size(min = 8, max = 32, message = "{password.invalid_size}")
    @field:NotBlank(message = "{request.password.is_blank}")
    val password: String
)
