package ru.mudan.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.mudan.domain.RefreshTokenModel
import java.util.*

@Repository
interface RefreshTokenRepository: JpaRepository<RefreshTokenModel, Long> {
    fun findByToken(token: String): Optional<RefreshTokenModel>
}