package com.sparta.hub.presentation.api.response

import com.sparta.hub.application.dto.response.HubDetailResponseDto

data class HubDetailResponse(
    val name: String,
    val address: String,
    val longitude: Double?,
    val latitude: Double?,
    val connectedHubList: List<HubRouteResponse>
)

fun HubDetailResponseDto.toResponse() = HubDetailResponse(
    name = name,
    address = address,
    longitude = longitude,
    latitude = latitude,
    connectedHubList = connectedHubList.map { it.toResponse() }
)