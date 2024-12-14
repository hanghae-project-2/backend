package com.sparta.hub.presentation.api.response

import com.sparta.hub.application.dto.response.HubRouteDetailResponseDto
import java.util.*

data class HubRouteDetailResponse(
    val hubRouteId: UUID,
    val startHubId: UUID,
    val startHubName: String,
    val endHubId: UUID,
    val endHubName: String,
    val estimatedSecond: Int,
    val estimatedMeter: Int,
)

fun HubRouteDetailResponseDto.toResponse() = HubRouteDetailResponse(
    hubRouteId = hubRouteId,
    startHubId = startHubId,
    startHubName = startHubName,
    endHubId = endHubId,
    endHubName = endHubName,
    estimatedSecond = estimatedSecond,
    estimatedMeter = estimatedMeter,
)