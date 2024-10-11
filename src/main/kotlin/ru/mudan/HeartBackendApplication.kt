package ru.mudan

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HeartBackendApplication

fun main(args: Array<String>) {
	runApplication<HeartBackendApplication>(*args)
}