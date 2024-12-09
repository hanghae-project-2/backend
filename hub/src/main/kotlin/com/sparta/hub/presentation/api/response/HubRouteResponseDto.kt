package com.sparta.hub.presentation.api.response

data class HubRouteResponseDto(

    val startHubName: String,
    val endHubName: String,
    val estimatedSecond: Int,
    val estimatedMeter: Int,
)
