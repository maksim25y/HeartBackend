package ru.mudan.service.images

import ru.mudan.controller.images.payload.IdResponse
import ru.mudan.controller.images.payload.ImageRequest
import ru.mudan.controller.images.payload.ImageResponse
import ru.mudan.controller.images.payload.ListImagesResponse

interface ImageService {
    fun add(imageRequest: ImageRequest): IdResponse
    val list: ListImagesResponse

    fun get(id: Long): ImageResponse?
}
