package ru.mudan.controller.security.payload

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class RegisterRequest(
    @field:JsonProperty("login")
    @field:NotBlank(message = "{register.request.login.is_blank}")
    @field:Size(min = 3, max = 15, message = "{register.request.login.invalid_size}")
    @field:Schema(name = "login", example = "test_login")
    val login: String,
    @field:Schema(name = "display_name", example = "test_name")
    @field:NotBlank(message = "{register.request.display_name.is_blank}")
    @field:JsonProperty("display_name") @param:JsonProperty("display_name") val displayName: String,
    @field:JsonProperty("password")
    @field:NotBlank(message = "{register.request.password.is_blank}")
    @field:Size(min = 8, max = 20, message = "{register.request.password.invalid_size}")
    @field:Schema(name = "password", example = "test_password")
    val password: String
)
