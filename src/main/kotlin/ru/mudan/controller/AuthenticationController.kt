package ru.mudan.controller

import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.mudan.controller.payload.AuthenticationRequest
import ru.mudan.controller.payload.AuthenticationResponse
import ru.mudan.controller.payload.RegisterRequest
import ru.mudan.secutiry.AuthenticationService

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
class AuthenticationController(val authenticationService: AuthenticationService) {

    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest): AuthenticationResponse? {
        return authenticationService.register(request)
    }

    @PostMapping("/authenticate")
    fun authenticate(@RequestBody request: AuthenticationRequest): AuthenticationResponse {
        return authenticationService.authenticate(request)
    }
}