package ru.mudan.controller

import lombok.RequiredArgsConstructor
import org.springframework.context.MessageSource
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.validation.BindException
import org.springframework.validation.ObjectError
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.*

@RestControllerAdvice
@RequiredArgsConstructor
class ControllerExceptionHandler (val messageSource: MessageSource) {
    @ExceptionHandler(NoSuchElementException::class)
    fun notFoundException(exception: NoSuchElementException, locale: Locale): ResponseEntity<ProblemDetail> {
        return createProblemDetailResponseEntity(
            HttpStatus.NOT_FOUND, messageSource.getMessage(
                "errors.404.title", arrayOfNulls(0), "errors.404.title", locale
            )!!, exception.message!!, locale
        )
    }

    @ExceptionHandler(BadCredentialsException::class)
    fun unauthorizedException(e: BadCredentialsException): ResponseEntity<*> {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.message)
    }

    @ExceptionHandler(BindException::class)
    fun handleBindException(exception: BindException, locale: Locale?): ResponseEntity<ProblemDetail> {
        val errorMessages = exception.allErrors.stream()
            .map { obj: ObjectError -> obj.defaultMessage }
            .toList()

        return createProblemDetailResponseEntity(
            HttpStatus.BAD_REQUEST, messageSource.getMessage(
                "errors.400.title", arrayOfNulls(0), "errors.400.title", locale!!
            )!!, errorMessages, locale
        )
    }

    private fun createProblemDetailResponseEntity(
        status: HttpStatus,
        messageKey: String,
        errorDetails: Any,
        locale: Locale
    ): ResponseEntity<ProblemDetail> {
        val message = Objects.requireNonNull<String>(
            messageSource.getMessage(
                messageKey, arrayOfNulls<Any>(0), messageKey, locale
            )
        )

        val problemDetail = ProblemDetail.forStatusAndDetail(status, message)
        problemDetail.setProperty("errors", errorDetails)

        return ResponseEntity.status(status).body(problemDetail)
    }
}
