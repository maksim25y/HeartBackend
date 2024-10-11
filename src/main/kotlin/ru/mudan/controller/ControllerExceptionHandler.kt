package ru.mudan.controller

import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
@RequiredArgsConstructor
class ControllerExceptionHandler {
    @ExceptionHandler(BadCredentialsException::class)
    fun unauthorizedException(e: BadCredentialsException): ResponseEntity<*> {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.message)
    }
}
