package ru.mudan.controller.images.payload

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotNull

@JvmRecord
data class ListImagesResponse(
    @Schema(name = "images", description = "Список всех изображений")
    val images: @NotNull MutableList<ImageResponse>?)