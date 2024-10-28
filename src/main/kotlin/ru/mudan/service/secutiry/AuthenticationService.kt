package ru.mudan.service.secutiry

import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import ru.mudan.controller.security.payload.AuthenticationRequest
import ru.mudan.controller.security.payload.AuthenticationResponse
import ru.mudan.controller.security.payload.RegisterRequest
import ru.mudan.domain.RefreshTokenModel
import ru.mudan.domain.entity.ApplicationUser
import ru.mudan.domain.entity.enums.Role
import ru.mudan.domain.repository.ApplicationUserRepository
import ru.mudan.domain.repository.RefreshTokenRepository
import ru.mudan.exception.UserExistsException

@Service
@RequiredArgsConstructor
class AuthenticationService(
    val appUserRepository: ApplicationUserRepository,
    val authenticationManager: AuthenticationManager,
    val passwordEncoder: PasswordEncoder,
    val jwtService: JwtService,
    val refreshTokenRepository: RefreshTokenRepository,
    @Qualifier("userDetailsService") private val userDetailsService: UserDetailsService
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
        val refreshToken = jwtService.generateRefreshToken(java.util.Map.of(),user)

        val refreshModel = RefreshTokenModel(user.username, refreshToken);

        refreshTokenRepository.save(refreshModel)

        return AuthenticationResponse(jwtToken,refreshToken)
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
        val refreshToken = jwtService.generateRefreshToken(java.util.Map.of(),user)

        val refreshModel = RefreshTokenModel(user.username, refreshToken)

        refreshTokenRepository.save(refreshModel)

        return AuthenticationResponse(jwtToken,refreshToken)
    }

    fun refreshAccessToken(refreshToken: String): AuthenticationResponse {
        val username = jwtService.extractEmail(refreshToken)

        return username.let { user ->
            val currentUserDetails = userDetailsService.loadUserByUsername(user)
            val tokenFromDB = refreshTokenRepository.findByToken(refreshToken).orElseThrow()
            val userEmail = tokenFromDB.email

            if (currentUserDetails.username == userEmail){
                refreshTokenRepository.deleteById(tokenFromDB.id)

                val refreshNewToken = jwtService.generateRefreshToken(java.util.Map.of(),currentUserDetails)
                val refreshModel = RefreshTokenModel(userEmail, refreshNewToken)

                refreshTokenRepository.save(refreshModel)
                AuthenticationResponse(jwtService.generateToken(currentUserDetails),
                    refreshNewToken)
            }else
                throw AuthenticationServiceException("Invalid refresh token")
        }
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