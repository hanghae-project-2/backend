package com.sparta.hub.presentation.api.controller

import com.sparta.hub.domain.service.HubService
import com.sparta.hub.presentation.api.response.Response
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/hubs")
class HubController(
    private val hubService: HubService,
) {

    @PostMapping
    fun registerHub(@RequestParam address: String, @RequestParam hubName: String): Response<UUID> =
        Response(
            HttpStatus.CREATED.value(),
            HttpStatus.CREATED.reasonPhrase,
            hubService.registerHub(address, hubName)
        )

    @PostMapping("/navigate")
    fun navigateHubRoutes(): Response<Unit> =
        Response(
            HttpStatus.OK.value(),
            HttpStatus.OK.reasonPhrase,
            hubService.navigateHubRoutes()
        )

}
