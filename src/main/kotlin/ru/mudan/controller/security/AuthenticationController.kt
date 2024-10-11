package ru.mudan.controller.security

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.mudan.controller.images.payload.IdResponse
import ru.mudan.controller.security.payload.AuthenticationRequest
import ru.mudan.controller.security.payload.AuthenticationResponse
import ru.mudan.controller.security.payload.RegisterRequest
import ru.mudan.service.secutiry.AuthenticationService

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
class AuthenticationController(val authenticationService: AuthenticationService) {
    @Operation(summary = "Зарегистрировать аккаунт")
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            description = "Аккаунт успешно зарегистрирован",
            content = arrayOf(Content(mediaType = "application/json", schema = Schema(implementation = AuthenticationResponse::class)))
        ), ApiResponse(responseCode = "400", description = "Некорректные параметры запроса")]
    )
    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest): AuthenticationResponse {
        return authenticationService.register(request)
    }
    @Operation(summary = "Войти в аккаунт")
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            description = "Вход успешно произведён",
            content = arrayOf(Content(mediaType = "application/json", schema = Schema(implementation = AuthenticationResponse::class)))
        ), ApiResponse(responseCode = "400", description = "Некорректные параметры запроса")]
    )
    @PostMapping("/authenticate")
    fun authenticate(@RequestBody request: AuthenticationRequest): AuthenticationResponse {
        return authenticationService.authenticate(request)
    }
}