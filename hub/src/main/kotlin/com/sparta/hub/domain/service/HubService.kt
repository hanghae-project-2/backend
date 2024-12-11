package com.sparta.hub.domain.service

import com.sparta.hub.application.dto.RouteResult
import com.sparta.hub.application.dto.request.HubRequestDto
import com.sparta.hub.application.dto.request.HubSearchRequestDto
import com.sparta.hub.application.dto.response.HubDetailResponseDto
import com.sparta.hub.application.dto.response.HubSummaryResponseDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface HubService {

    fun registerHub(hubAddress: String, hubName: String): UUID

    fun navigateHubRoutes()

    fun getOptimalHubRoutes(startHubName: String, endHubName: String): RouteResult

    fun getHubs(pageable: Pageable, requestDto: HubSearchRequestDto): Page<HubSummaryResponseDto>

    fun getHubDetail(hubId: UUID): HubDetailResponseDto

    fun modifyHub(hubId: UUID, hubRequestDto: HubRequestDto): UUID

    fun existHub(hubId: UUID): Boolean
}
