package com.sparta.hub.application.dto.response

import java.util.*

data class HubRouteDetailResponseDto(
    val hubRouteId: UUID,
    val startHubId: UUID,
    val startHubName: String,
    val endHubId: UUID,
    val endHubName: String,
    val estimatedSecond: Int,
    val estimatedMeter: Int,
)
