package ru.mudan.service.images

import org.springframework.data.domain.Pageable;
import ru.mudan.controller.images.payload.ImageRequest
import ru.mudan.controller.images.payload.ImageResponse

interface ImageService {
    fun add(imageRequest: ImageRequest): ImageResponse

    fun list(pageable: Pageable): Iterable<ImageResponse>

    fun get(id: Long): ImageResponse
}
