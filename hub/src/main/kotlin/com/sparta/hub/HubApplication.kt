package com.sparta.hub

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.data.jpa.repository.config.EnableJpaAuditing


@EnableCaching
@EnableJpaAuditing
@SpringBootApplication
class HubApplication

fun main(args: Array<String>) {
    runApplication<HubApplication>(*args)
}
