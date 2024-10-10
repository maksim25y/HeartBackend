package ru.mudan.controller.payload

import lombok.Builder

@JvmRecord
data class AuthenticationResponse(
    val token: String
)
