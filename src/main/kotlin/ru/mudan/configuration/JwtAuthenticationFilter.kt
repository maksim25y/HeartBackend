package ru.mudan.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.ExpiredJwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.RequiredArgsConstructor
import org.springframework.context.MessageSource
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ProblemDetail
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import ru.mudan.service.secutiry.JwtService
import java.io.IOException
import java.net.URI
import java.nio.charset.StandardCharsets
import java.util.*

@Component
@RequiredArgsConstructor
class JwtAuthenticationFilter(val jwtService: JwtService,
                              val userDetailsService: UserDetailsService,
                              val objectMapper: ObjectMapper,
                              val messageSource: MessageSource) :
    OncePerRequestFilter() {
    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val authHeader = request.getHeader("Authorization")
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response)
                return
            }
            val jwt = authHeader.substring(7)
            val userEmail = jwtService.extractEmail(jwt)

            if (userEmail != null && SecurityContextHolder.getContext().authentication == null) {
                val userDetails = userDetailsService.loadUserByUsername(userEmail)
                if (jwtService.isTokenValid(jwt, userDetails)) {
                    val authToken = UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.authorities
                    )
                    authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                    SecurityContextHolder.getContext().authentication = authToken
                    filterChain.doFilter(request, response)
                }
            }
        }catch (e: ExpiredJwtException){
            println("Токен просрочен")
            filterChain.doFilter(request, response)
        }
    }
}
