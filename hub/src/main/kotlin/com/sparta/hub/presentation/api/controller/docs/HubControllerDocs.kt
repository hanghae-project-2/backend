package com.sparta.hub.presentation.api.controller.docs

import com.sparta.hub.application.dto.RouteResult
import com.sparta.hub.presentation.api.request.HubRequest
import com.sparta.hub.presentation.api.request.HubSearchRequest
import com.sparta.hub.presentation.api.response.HubDetailResponse
import com.sparta.hub.presentation.api.response.HubSummaryResponse
import com.sparta.hub.presentation.api.response.Response
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

@Tag(name = "Hub", description = "허브 API")
abstract class HubControllerDocs {

    @Operation(summary = "허브 등록", description = "허브를 등록하는 API 입니다.")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201",
                description = "허브 등록 성공",
                content = [Content(schema = Schema(implementation = Response::class))]
            )
        ]
    )
    @PostMapping("/hubs")
    abstract fun registerHub(
        @RequestParam address: String,
        @RequestParam hubName: String,
        servletRequest: HttpServletRequest
    ): Response<UUID>

    @Operation(summary = "허브 간 경로 탐색", description = "전체 허브 간 경로를 탐색하여 결과를 등록하는 API 입니다.")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201",
                description = "경로 탐색 성공",
                content = [Content(schema = Schema(implementation = Response::class))]
            ),
            ApiResponse(
                responseCode = "400",
                description = "경로를 계산할 수 없습니다.",
                content = [Content(schema = Schema(implementation = Response::class))]
            ),
            ApiResponse(
                responseCode = "404",
                description = "허브를 찾을 수 없습니다.",
                content = [Content(schema = Schema(implementation = Response::class))]
            )
        ]
    )
    @PatchMapping("/hubs/navigate")
    abstract fun navigateHubRoutes(
        servletRequest: HttpServletRequest
    ): Response<Unit>

    @Operation(summary = "특정 허브 간 경로 탐색", description = "전달받은 허브 간 경로를 탐색하여 결과를 등록하는 API 입니다.")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201",
                description = "경로 탐색 성공",
                content = [Content(schema = Schema(implementation = Response::class))]
            ),
            ApiResponse(
                responseCode = "400",
                description = "경로를 계산할 수 없습니다.",
                content = [Content(schema = Schema(implementation = Response::class))]
            ),
            ApiResponse(
                responseCode = "404",
                description = "허브를 찾을 수 없습니다.",
                content = [Content(schema = Schema(implementation = Response::class))]
            )
        ]
    )
    @GetMapping("/hubs/routes/name")
    abstract fun findHubRoutesByName(
        @RequestParam startHubName: String,
        @RequestParam endHubName: String
    ): Response<RouteResult>


    @Operation(summary = "허브 목록 조회", description = "전체 허브 목록을 조회하는 API 입니다.")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "허브 목록 조회 성공",
                content = [Content(schema = Schema(implementation = Response::class))]
            )
        ]
    )
    @GetMapping("/hubs/search")
    abstract fun searchHubs(
        pageable: Pageable,
        hubSearchRequest: HubSearchRequest
    ): Response<Page<HubSummaryResponse>>

    @Operation(summary = "허브 상세 조회", description = "허브를 상세 조회하는 API 입니다.")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "허브 조회 성공",
                content = [Content(schema = Schema(implementation = Response::class))]
            )
        ]
    )
    @GetMapping("/hubs/{hubId}")
    abstract fun getHubDetail(
        @PathVariable hubId: UUID
    ): Response<HubDetailResponse>

    @Operation(summary = "허브 수정", description = "허브를 수정하는 API 입니다.")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "허브 수정 성공",
                content = [Content(schema = Schema(implementation = Response::class))]
            )
        ]
    )
    @PatchMapping("/hubs/{hubId}")
    abstract fun modifyHub(
        @PathVariable hubId: UUID,
        @RequestBody request: HubRequest,
        servletRequest: HttpServletRequest
    ): Response<UUID>

}