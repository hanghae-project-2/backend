package com.sparta.hub.presentation.api.controller

import com.sparta.hub.application.dto.RouteResult
import com.sparta.hub.application.service.HubService
import com.sparta.hub.presentation.api.response.HubResponse
import com.sparta.hub.presentation.api.response.HubRouteDetailResponse
import com.sparta.hub.presentation.api.response.toResponse
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

    @GetMapping("/hubs/company/{hubId}")
    fun existHub(
        @PathVariable hubId: UUID
    ): Boolean =
        hubService.existHub(hubId)

    @GetMapping("/hubs/search-by-name")
    fun findHubByName(
        @RequestParam hubName: String
    ): HubResponse =
        hubService.findHubByName(hubName).toResponse()

    @GetMapping("/hubs/find/{hubId}")
    fun findHubById(
        @PathVariable hubId: UUID
    ): HubResponse =
        hubService.findHubById(hubId).toResponse()

    @PostMapping("/hubs/batch")
    fun findHubsByIds(
        @RequestBody ids: List<UUID>
    ): List<HubResponse> =
        hubService.findHubsByIds(ids).map { it.toResponse() }

    @PostMapping("/hubs/routes")
    fun findHubRoutesByHubRouteId(
        @RequestBody hubRouteIdList: List<UUID>
    ): List<HubRouteDetailResponse> =
        hubService.findHubRoutesByHubRouteId(hubRouteIdList).map { it.toResponse() }
}