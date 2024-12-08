package com.sparta.hub

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing


@EnableJpaAuditing
@SpringBootApplication
class HubApplication

fun main(args: Array<String>) {
    runApplication<HubApplication>(*args)
}
