package ru.mudan.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.mudan.domain.entity.ApplicationUser
import java.util.*

@Repository
interface ApplicationUserRepository : JpaRepository<ApplicationUser?, Long?> {
    fun findByEmail(login: String?): Optional<ApplicationUser?>
}
