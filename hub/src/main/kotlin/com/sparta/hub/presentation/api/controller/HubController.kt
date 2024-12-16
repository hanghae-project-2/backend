package com.sparta.hub.presentation.api.controller

import com.sparta.hub.application.dto.RouteResult
import com.sparta.hub.application.service.HubService
import com.sparta.hub.libs.RoleValidation
import com.sparta.hub.presentation.api.controller.docs.HubControllerDocs
import com.sparta.hub.presentation.api.request.HubRequest
import com.sparta.hub.presentation.api.request.HubSearchRequest
import com.sparta.hub.presentation.api.request.toDto
import com.sparta.hub.presentation.api.response.HubDetailResponse
import com.sparta.hub.presentation.api.response.HubSummaryResponse
import com.sparta.hub.presentation.api.response.Response
import com.sparta.hub.presentation.api.response.toResponse
import jakarta.servlet.http.HttpServletRequest
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
    @RoleValidation("HUB_ADMIN")
    override fun registerHub(
        @RequestParam address: String,
        @RequestParam hubName: String,
        servletRequest: HttpServletRequest
    ): Response<UUID> =
        Response(
            HttpStatus.CREATED.value(),
            HttpStatus.CREATED.reasonPhrase,
            hubService.registerHub(servletRequest, address, hubName)
        )

    @PatchMapping("/navigate")
    @RoleValidation("HUB_ADMIN")
    override fun navigateHubRoutes(
        servletRequest: HttpServletRequest
    ): Response<Unit> =
        Response(
            HttpStatus.OK.value(),
            HttpStatus.OK.reasonPhrase,
            hubService.navigateHubRoutes(servletRequest)
        )

    @GetMapping("/routes/name")
    @RoleValidation("ANY_ROLE")
    override fun findHubRoutesByName(
        @RequestParam startHubName: String,
        @RequestParam endHubName: String
    ): Response<RouteResult> =
        Response(
            HttpStatus.OK.value(),
            HttpStatus.OK.reasonPhrase,
            hubService.getOptimalHubRoutes(startHubName, endHubName)
        )

    @GetMapping("/search")
    @RoleValidation("ANY_ROLE")
    override fun searchHubs(
        pageable: Pageable,
        hubSearchRequest: HubSearchRequest
    ): Response<Page<HubSummaryResponse>> =
        Response(
            HttpStatus.OK.value(),
            HttpStatus.OK.reasonPhrase,
            hubService.searchHubs(pageable, hubSearchRequest.toDto()).toResponse()
        )

    @GetMapping("/{hubId}")
    @RoleValidation("ANY_ROLE")
    override fun getHubDetail(
        @PathVariable hubId: UUID
    ): Response<HubDetailResponse> =
        Response(
            HttpStatus.OK.value(),
            HttpStatus.OK.reasonPhrase,
            hubService.getHubDetail(hubId).toResponse()
        )

    @PatchMapping("/{hubId}")
    @RoleValidation("HUB_ADMIN")
    override fun modifyHub(
        @PathVariable hubId: UUID,
        @RequestBody request: HubRequest,
        servletRequest: HttpServletRequest
    ): Response<UUID> =
        Response(
            HttpStatus.OK.value(),
            HttpStatus.OK.reasonPhrase,
            hubService.modifyHub(servletRequest, hubId, request.toDto())
        )
}
