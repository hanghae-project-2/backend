package com.sparta.hub.presentation.api.controller

import com.sparta.hub.application.dto.RouteResult
import com.sparta.hub.domain.service.HubService
import com.sparta.hub.presentation.api.controller.docs.HubControllerDocs
import com.sparta.hub.presentation.api.request.HubRequest
import com.sparta.hub.presentation.api.request.HubSearchRequest
import com.sparta.hub.presentation.api.request.toDto
import com.sparta.hub.presentation.api.response.HubDetailResponse
import com.sparta.hub.presentation.api.response.HubSummaryResponse
import com.sparta.hub.presentation.api.response.Response
import com.sparta.hub.presentation.api.response.toResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/hubs")
class HubController(
    private val hubService: HubService,
) : HubControllerDocs() {

    @PostMapping
    override fun registerHub(
        @RequestParam address: String,
        @RequestParam hubName: String
    ): Response<UUID> =
        Response(
            HttpStatus.CREATED.value(),
            HttpStatus.CREATED.reasonPhrase,
            hubService.registerHub(address, hubName)
        )

    @PostMapping("/navigate")
    override fun navigateHubRoutes(): Response<Unit> =
        Response(
            HttpStatus.OK.value(),
            HttpStatus.OK.reasonPhrase,
            hubService.navigateHubRoutes()
        )

    @GetMapping("/routes")
    override fun getHubRoutes(
        @RequestParam startHubName: String,
        @RequestParam endHubName: String
    ): Response<RouteResult> =
        Response(
            HttpStatus.OK.value(),
            HttpStatus.OK.reasonPhrase,
            hubService.getOptimalHubRoutes(startHubName, endHubName)
        )

    @GetMapping
    override fun getHubs(
        pageable: Pageable,
        hubSearchRequest: HubSearchRequest
    ): Response<Page<HubSummaryResponse>> =
        Response(
            HttpStatus.OK.value(),
            HttpStatus.OK.reasonPhrase,
            hubService.getHubs(pageable, hubSearchRequest.toDto()).toResponse()
        )

    @GetMapping("/{hubId}")
    override fun getHubDetail(
        @PathVariable hubId: UUID
    ): Response<HubDetailResponse> =
        Response(
            HttpStatus.OK.value(),
            HttpStatus.OK.reasonPhrase,
            hubService.getHubDetail(hubId).toResponse()
        )

    @PatchMapping("/{hubId}")
    override fun modifyHub(
        @PathVariable hubId: UUID,
        @RequestBody request: HubRequest
    ): Response<UUID> =
        Response(
            HttpStatus.OK.value(),
            HttpStatus.OK.reasonPhrase,
            hubService.modifyHub(hubId, request.toDto())
        )

    @GetMapping("/company/{hubId}")
    fun existHub(
        @PathVariable hubId: UUID
    ): Boolean = hubService.existHub(hubId)
}
