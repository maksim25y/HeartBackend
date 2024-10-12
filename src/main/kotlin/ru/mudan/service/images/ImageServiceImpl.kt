package ru.mudan.service.images

import lombok.RequiredArgsConstructor
import org.springframework.context.MessageSource
import org.springframework.stereotype.Service
import ru.mudan.controller.images.payload.IdResponse
import ru.mudan.controller.images.payload.ImageRequest
import ru.mudan.controller.images.payload.ImageResponse
import ru.mudan.controller.images.payload.ListImagesResponse
import ru.mudan.domain.entity.ImageEntity
import ru.mudan.domain.repository.ImageRepository
import java.util.*

@RequiredArgsConstructor
@Service
class ImageServiceImpl(val imageRepository: ImageRepository, val messageSource: MessageSource) : ImageService{

    override fun add(imageRequest: ImageRequest): IdResponse {
        val image = ImageEntity(
            imageRequest.image!!, imageRequest.title!!,
            imageRequest.creation_date!!, imageRequest.description!!
        )
        imageRepository.saveAndFlush(image)
        return IdResponse(image.id)
    }

    override val list: ListImagesResponse
        get() {
            val entityList = imageRepository.findAll()
            val list: MutableList<ImageResponse> = ArrayList()
            for (image in entityList) {
                list.add(ImageResponse(image!!.image, image.title, image.creationDate, image.description))
            }
            return ListImagesResponse(list)
        }

    override fun get(id: Long): ImageResponse? {
        val entity = imageRepository.findById(id)
        if (entity.isEmpty) {
            throw getDocumentTypeNoSuchElementException(id)
        }
        val image = entity.get()
        return ImageResponse(image.image, image.title, image.creationDate, image.description)
    }
    private fun getDocumentTypeNoSuchElementException(id: Long): NoSuchElementException {
        return NoSuchElementException(
            messageSource.getMessage("image.not.found", arrayOf<Any>(id), Locale.getDefault())
        )
    }
}
