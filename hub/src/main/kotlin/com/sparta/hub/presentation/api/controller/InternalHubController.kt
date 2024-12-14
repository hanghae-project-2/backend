package com.sparta.hub.presentation.api.controller

import com.sparta.hub.application.dto.RouteResult
import com.sparta.hub.application.service.HubService
import com.sparta.hub.presentation.api.response.HubResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class InternalHubController(
    private val hubService: HubService,
) {

    @GetMapping("/hubs/routes/id")
    fun findHubRoutesById(
        @RequestParam startHubId: UUID,
        @RequestParam endHubId: UUID
    ): RouteResult =
        hubService.getOptimalHubRoutes(startHubId, endHubId)

    //TODO: 왜 List<HubResponse> 를 리턴하는지 확인. 아마 name 이 일치하는 hub 를 찾는것같은데..?
    @GetMapping("/hubs/search-by-name")
    fun findHubByName(
        @RequestParam hubName: String
    ): HubResponse =
        hubService.findHubByName(hubName)

    @GetMapping("/hubs/find/{hubId}")
    fun findHubById(
        @PathVariable hubId: UUID
    ): HubResponse =
        hubService.findHubById(hubId)

    @PostMapping("/hubs/batch")
    fun findHubsByIds(
        @RequestBody ids: List<UUID>
    ): List<HubResponse> =
        hubService.findHubsByIds(ids)
}