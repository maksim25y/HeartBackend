package ru.mudan.service.images

import lombok.RequiredArgsConstructor
import org.springframework.context.MessageSource
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import ru.mudan.controller.images.payload.ImageRequest
import ru.mudan.controller.images.payload.ImageResponse
import ru.mudan.domain.entity.ImageEntity
import ru.mudan.domain.repository.ImageRepository
import java.util.*

@RequiredArgsConstructor
@Service
class ImageServiceImpl(val imageRepository: ImageRepository, val messageSource: MessageSource) : ImageService{

    override fun add(imageRequest: ImageRequest): ImageResponse {
        val image = ImageEntity(
            imageRequest.image!!, imageRequest.title!!,
            imageRequest.creation_date!!, imageRequest.description!!
        )
        var createdImage = imageRepository.saveAndFlush(image)
        return ImageResponse(
            image = createdImage!!.image,
            title = createdImage!!.title,
            creation_date = createdImage!!.creationDate,
            description = createdImage!!.description,
            id = createdImage!!.id
        )
    }

    override fun list(pageable: Pageable): Iterable<ImageResponse> {
        val images = imageRepository.findAll(pageable)

        return images.map { imageEntity ->
            ImageResponse(
                image = imageEntity!!.image,
                title = imageEntity!!.title,
                creation_date = imageEntity!!.creationDate,
                description = imageEntity!!.description,
                id = imageEntity!!.id
            )
        }
    }

    override fun get(id: Long): ImageResponse {
        val entity = imageRepository.findById(id)
        if (entity.isEmpty) {
            throw getDocumentTypeNoSuchElementException(id)
        }
        val image = entity.get()
        return ImageResponse(image.id, image.image, image.title, image.creationDate, image.description)
    }
    private fun getDocumentTypeNoSuchElementException(id: Long): NoSuchElementException {
        return NoSuchElementException(
            messageSource.getMessage("image.not.found", arrayOf<Any>(id), Locale.getDefault())
        )
    }
}
