package com.sparta.hub.presentation.api.response

import com.sparta.hub.application.dto.response.HubRouteResponseDto

data class HubRouteResponse(
    val startHubName: String,
    val endHubName: String,
    val estimatedSecond: Int,
    val estimatedMeter: Int,
)

fun HubRouteResponseDto.toResponse() = HubRouteResponse(
    startHubName = startHubName,
    endHubName = endHubName,
    estimatedSecond = estimatedSecond,
    estimatedMeter = estimatedMeter,
)