package ru.mudan.domain.entity

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import java.time.OffsetDateTime


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "images")
data class ImageEntity(
    val image: String,
    val title: String,
    val creationDate: OffsetDateTime,
    val description: String
){
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
val id: Long = 0}