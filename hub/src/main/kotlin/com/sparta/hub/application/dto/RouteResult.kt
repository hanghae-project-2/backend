package com.sparta.hub.application.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.sparta.hub.presentation.api.response.HubRouteResponseDto

data class RouteResult(
    @JsonProperty("distance")
    val distance: Int?,
    @JsonProperty("time")
    val time: Int?,
    @JsonProperty("path")
    val path: List<String>,
)

fun RouteResult.toDto() = HubRouteResponseDto(
    startHubName = path.first(),
    endHubName = path.last(),
    estimatedSecond = time!!,
    estimatedMeter = distance!!,
)