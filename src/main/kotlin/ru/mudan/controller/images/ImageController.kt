package ru.mudan.controller.images

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import jakarta.validation.constraints.Positive
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.ErrorResponse
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.HttpClientErrorException
import ru.mudan.controller.images.payload.IdResponse
import ru.mudan.controller.images.payload.ImageRequest
import ru.mudan.controller.images.payload.ImageResponse
import ru.mudan.controller.images.payload.ListImagesResponse
import ru.mudan.service.images.ImageService
import java.util.*


@SecurityRequirement(name = "JWT")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/images")
@Tag(name = "Изображения", description = "API для взаимодействия с изображениями")
class ImageController(val imageServiceImpl: ImageService) {
    @Operation(summary = "Добавить изображение")
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            description = "Изображение добавлено",
            content = arrayOf(Content(mediaType = "application/json", schema = Schema(implementation = IdResponse::class)))
        ), ApiResponse(responseCode = "400", description = "Некорректные параметры запроса")]
    )
    @PostMapping
    fun addFile(@Valid @RequestBody imageRequest: ImageRequest): ResponseEntity<ImageResponse> {
        return ResponseEntity.ok(imageServiceImpl.add(imageRequest))
    }

    @Operation(summary = "Получить изображение")
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            description = "Изображение успешно получено",
            content = arrayOf(Content(mediaType = "application/json", schema = Schema(implementation = ImageResponse::class)))
        ), ApiResponse(
            responseCode = "400",
            description = "Некорректные параметры запроса"
        ), ApiResponse(
            responseCode = "404",
            description = "Изображение не найдено",
            content = arrayOf(Content(mediaType = "application/json", schema = Schema(implementation = ErrorResponse::class)))
        )]
    )
    @GetMapping(path = ["/image/{id}"])
    fun getFile(@PathVariable id: @Positive Long): ResponseEntity<ImageResponse> {
        val image = imageServiceImpl.get(id)
        if (Objects.isNull(image)) {
            throw HttpClientErrorException.NotFound.create(
                HttpStatusCode.valueOf(404),
                "Изображение не найдено", HttpHeaders.EMPTY, byteArrayOf(), null
            )
        }
        return ResponseEntity.ok(image)
    }

    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            description = "Изображения успешно получены",
            content = arrayOf(
                Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = ListImagesResponse::class)
                )
            )
        ), ApiResponse(
            responseCode = "400",
            description = "Некорректные параметры запроса"
        )]
    )
    @Operation(summary = "Получить все изображения")
    @GetMapping(path = ["/all/{page}"])
    fun getFiles(
        @PathVariable page: Int,
        @RequestParam(name = "size", required = false, defaultValue = "5") pageSize: Int
    ): Iterable<ImageResponse> {
        val pageable: Pageable = PageRequest
            .of(
                page, pageSize
            )
        return imageServiceImpl.list(pageable)
    }
}
