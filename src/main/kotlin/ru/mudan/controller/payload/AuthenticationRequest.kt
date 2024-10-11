package ru.mudan.controller.payload

import io.swagger.v3.oas.annotations.media.Schema
import lombok.Builder

@Builder
@JvmRecord
data class AuthenticationRequest(
    @Schema(name = "login", example = "test_login")
    val login: String,
    @Schema(name = "password", example = "test_password")
    val password: String
)
