package ru.mudan.service.images

import lombok.RequiredArgsConstructor
import org.springframework.context.MessageSource
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import ru.mudan.controller.images.payload.ImageRequest
import ru.mudan.controller.images.payload.ImageResponse
import ru.mudan.domain.entity.ImageEntity
import ru.mudan.domain.repository.ImageRepository
import ru.mudan.exception.ImageNotFoundException

@RequiredArgsConstructor
@Service
class ImageServiceImpl(val imageRepository: ImageRepository, val messageSource: MessageSource) : ImageService{

    override fun add(imageRequest: ImageRequest): ImageResponse {
        val image = ImageEntity(
            imageRequest.image!!, imageRequest.title!!,
            imageRequest.creation_date!!, imageRequest.description!!
        )
        val createdImage = imageRepository.saveAndFlush(image)
        return ImageResponse(
            image = createdImage.image,
            title = createdImage.title,
            creation_date = createdImage.creationDate,
            description = createdImage.description,
            id = createdImage.id
        )
    }

    override fun list(pageable: Pageable): Iterable<ImageResponse> {
        val images = imageRepository.findAll(pageable)

        return images.map { imageEntity ->
            ImageResponse(
                image = imageEntity!!.image,
                title = imageEntity.title,
                creation_date = imageEntity.creationDate,
                description = imageEntity.description,
                id = imageEntity.id
            )
        }
    }

    override fun get(id: Long): ImageResponse {
        val imageEntityOptional = imageRepository.findById(id)
        if (imageEntityOptional.isEmpty) {
            throw ImageNotFoundException(id)
        }
        val imageEntity = imageEntityOptional.get()
        return ImageResponse(
            image = imageEntity.image,
            title = imageEntity.title,
            creation_date = imageEntity.creationDate,
            description = imageEntity.description,
            id = imageEntity.id
        )
    }
}
