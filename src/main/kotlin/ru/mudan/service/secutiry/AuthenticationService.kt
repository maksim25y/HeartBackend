package ru.mudan.service.secutiry

import lombok.RequiredArgsConstructor
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import ru.mudan.controller.security.payload.AuthenticationRequest
import ru.mudan.controller.security.payload.AuthenticationResponse
import ru.mudan.controller.security.payload.RegisterRequest
import ru.mudan.domain.entity.ApplicationUser
import ru.mudan.domain.entity.enums.Role
import ru.mudan.domain.repository.ApplicationUserRepository
import ru.mudan.exception.UserExistsException

@Service
@RequiredArgsConstructor
class AuthenticationService( val appUserRepository: ApplicationUserRepository,
        val authenticationManager: AuthenticationManager,
         val passwordEncoder: PasswordEncoder,
         val jwtService: JwtService
) {
    fun register(request: RegisterRequest): AuthenticationResponse {
        checkEmail(request.email)

        val user =  ApplicationUser(request.email,
            request.firstname,
            request.lastname,
            request.patronymic,
            encodePassword(request.password),
            Role.USER);
        appUserRepository.save(user)
        val jwtToken = jwtService.generateToken(user)
        return AuthenticationResponse(jwtToken)
    }

    fun authenticate(request: AuthenticationRequest): AuthenticationResponse {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                request.email,
                request.password
            )
        )

        val user = appUserRepository.findByEmail(request.email)
            .orElseThrow()!!
        val jwtToken = jwtService.generateToken(user)
        return AuthenticationResponse(jwtToken)
    }

    private fun checkEmail(email: String) {
        val applicationUser = appUserRepository.findByEmail(email)
        if (applicationUser.isPresent) {
            throw UserExistsException(email)
        }
    }

    fun encodePassword(password: String?): String {
        return passwordEncoder.encode(password)
    }
}