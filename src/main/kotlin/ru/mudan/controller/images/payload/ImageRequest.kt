package ru.mudan.controller.images.payload

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.OffsetDateTime

@JvmRecord
data class ImageRequest(
    @field:JsonProperty("image")
    @field:Schema(name = "image", example = "data:image/jpeg;base64,/9j/4AAQSkZJRg", description = "Изображение в формате png, jpg в base64")
    @field:NotBlank(message = "{image.request.name.is_blank}")
    val image: String?,
    @field:JsonProperty("title")
    @field:Schema(name = "title", example = "Исследование 1", description = "Название изображения")
    @field:NotBlank(message = "{image.request.title.is_blank}")
    val title: String?,
    @field:JsonProperty("creation_date")
    @field:Schema(name = "creation_date", example = "2024-10-11T16:33:49.76Z", description = "Дата загрузки изображения")
    val creation_date: OffsetDateTime?,
    @field:JsonProperty("description")
    @field:Schema(name = "description", example = "На изображении нарушений не обнаружено", description = "Описание изображения (комментарии)")
    @field:NotBlank(message = "{image.request.description.is_blank}")
    val description: String?
)