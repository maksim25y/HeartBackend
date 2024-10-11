package ru.mudan.controller.images.payload

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.time.OffsetDateTime

@JvmRecord
data class ImageRequest(
    @Schema(name = "image", example = "data:image/jpeg;base64,/9j/4AAQSkZJRg", description = "Изображение в формате png, jpg в base64")
    val image: @NotNull String,
    @Schema(name = "title", example = "Исследование 1", description = "Название изображения")
    val title: @NotEmpty String,
    @Schema(name = "creation_date", example = "2024-10-11T16:33:49.76Z", description = "Дата загрузки изображения")
    val creation_date: @NotNull OffsetDateTime,
    @Schema(name = "description", example = "На изображении нарушений не обнаружено", description = "Описание изображения (комментарии)")
    val description: @NotEmpty String
)