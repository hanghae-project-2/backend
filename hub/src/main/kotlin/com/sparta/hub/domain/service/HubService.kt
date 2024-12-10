package com.sparta.hub.domain.service

import com.sparta.hub.application.dto.RouteResult
import com.sparta.hub.presentation.api.request.HubRequestDto
import com.sparta.hub.presentation.api.response.HubDetailResponseDto
import com.sparta.hub.presentation.api.response.HubResponseDto
import java.util.*

interface HubService {

    fun registerHub(hubAddress: String, hubName: String): UUID

    fun navigateHubRoutes()

    fun getOptimalHubRoutes(startHubName: String, endHubName: String): RouteResult

    fun getHubs(): List<HubResponseDto>

    fun getHubDetail(hubId: UUID): HubDetailResponseDto

    fun modifyHub(hubId: UUID, hubRequestDto: HubRequestDto): UUID
}
