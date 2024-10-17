package ru.mudan.controller.images.payload

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.PositiveOrZero

@JvmRecord
data class IdResponse(
    @Schema(name = "id", example = "1", description = "ID сохранённого изображения")
    val id: @PositiveOrZero Long
)
