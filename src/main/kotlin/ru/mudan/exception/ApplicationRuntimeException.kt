package ru.mudan.exception

import lombok.EqualsAndHashCode

@EqualsAndHashCode(callSuper = true)
abstract class ApplicationRuntimeException(message: String?) : RuntimeException(message)
