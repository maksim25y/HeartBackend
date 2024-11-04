package ru.mudan.controller.security

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import lombok.RequiredArgsConstructor
import org.springframework.http.ProblemDetail
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.mudan.controller.security.payload.*
import ru.mudan.service.secutiry.AuthenticationService

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Аутентификация", description = "API управления аутентификацией")
class AuthenticationController(val authenticationService: AuthenticationService) {
    @Operation(
        summary = "Регистрация нового пользователя",
        description = "Регистрирует нового пользователя с предоставленной информацией"
    )
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            description = "Успешная регистрация",
            content = arrayOf(Content(schema = Schema(implementation = AuthenticationResponse::class)))
        ), ApiResponse(
            responseCode = "400",
            description = "Неверный ввод",
            content = arrayOf(Content(schema = Schema(implementation = ProblemDetail::class)))
        )]
    )
    @PostMapping("/register")
    fun register(@Valid @RequestBody request: RegisterRequest): AuthenticationResponse {
        return authenticationService.register(request)
    }

    @Operation(
        summary = "Аутентификация пользователя",
        description = "Аутентифицирует пользователя с предоставленными учетными данными"
    )
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            description = "Успешная аутентификация",
            content = arrayOf(Content(schema = Schema(implementation = AuthenticationResponse::class)))
        ), ApiResponse(
            responseCode = "400",
            description = "Неверный ввод",
            content = arrayOf(Content(schema = Schema(implementation = ProblemDetail::class)))
        ), ApiResponse(
            responseCode = "401",
            description = "Ошибка аутентификации",
            content = arrayOf(Content(schema = Schema(implementation = ProblemDetail::class)))
        )]
    )
    @PostMapping("/authenticate")
    fun authenticate(@Valid @RequestBody request: AuthenticationRequest): AuthenticationResponse {
        return authenticationService.authenticate(request)
    }

    @Operation(
        summary = "Обновление jwt токена по refresh токену.",
        description = "Обновляет просроченный jwt токен по refresh токену и возвращает новый jwt токен."
    )
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            description = "Успешное обновление токена",
            content = arrayOf(Content(schema = Schema(implementation = AuthenticationResponse::class)))
        )]
    )
    @PostMapping("/refresh")
    fun refreshAccessToken(
        @RequestBody request: RefreshTokenRequest):AuthenticationResponse{
        return authenticationService.refreshAccessToken(request.token)
    }
}