package ru.mudan.controller.payload

import com.fasterxml.jackson.annotation.JsonProperty

@JvmRecord
data class RegisterRequest(
    val login: String,
    @field:JsonProperty("display_name") @param:JsonProperty("display_name") val displayName: String,
    val password: String
)
