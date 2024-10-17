package ru.mudan.controller.security.payload

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

@Schema(description = "Запрос на регистрацию, содержащий информацию о новом пользователе")
data class RegisterRequest(
    @field:Schema(description = "Адрес электронной почты пользователя", example = "test@mail.ru")
    @field:Pattern(message = "{email.invalid}",
        regexp = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    @field:NotBlank(message = "{email.is_blank}")
    val email: String,
    @field:Schema(description = "Имя пользователя", example = "Иван")
    @field:NotBlank(message = "{firstname.is_blank}")
    val firstname: String,
    @field:Schema(description = "Фамилия пользователя", example = "Иванов")
    @field:NotBlank(message = "{lastname.is_blank}")
    val lastname: String,
    @field:Schema(description = "Отчество пользователя", example = "Иванович")
    @field:NotBlank(message = "{patronymic.is_blank}")
    val patronymic: String,
    @field:Schema(description = "Пароль пользователя", example = "securePassword123")
    @field:Size(min = 8, max = 32, message = "{password.invalid_size}")
    @field:NotBlank(message = "{request.password.is_blank}")
    val password: String
)
