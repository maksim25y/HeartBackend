package ru.mudan.controller.payload

import lombok.Builder

@Builder
@JvmRecord
data class AuthenticationRequest(
    val login: String,
    val password: String
)
