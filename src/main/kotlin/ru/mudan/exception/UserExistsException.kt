package ru.mudan.exception

import lombok.Getter
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Getter
data class UserExistsException(
    val email: String
) : ApplicationRuntimeException("user.email.is_busy")
