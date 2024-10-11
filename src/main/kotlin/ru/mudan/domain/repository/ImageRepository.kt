package ru.mudan.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.mudan.domain.entity.ImageEntity


@Repository
interface ImageRepository : JpaRepository<ImageEntity?, Long?>
