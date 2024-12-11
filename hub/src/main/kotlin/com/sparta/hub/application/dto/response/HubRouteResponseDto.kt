package com.sparta.hub.application.dto.response

data class HubRouteResponseDto(
    val startHubName: String,
    val endHubName: String,
    val estimatedSecond: Int,
    val estimatedMeter: Int,
)
