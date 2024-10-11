package ru.mudan.controller.payload

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

@JvmRecord
data class RegisterRequest(
    @Schema(name = "login", example = "test_login")
    val login: String,
    @Schema(name = "login", example = "test_name")
    @field:JsonProperty("display_name") @param:JsonProperty("display_name") val displayName: String,
    @Schema(name = "login", example = "test_password")
    val password: String
)
