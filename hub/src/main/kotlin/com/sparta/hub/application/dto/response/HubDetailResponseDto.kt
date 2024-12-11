package com.sparta.hub.application.dto.response

import com.sparta.hub.application.dto.RouteResult
import com.sparta.hub.application.dto.toDto
import com.sparta.hub.domain.model.Hub

data class HubDetailResponseDto(
    val name: String,
    val address: String,
    val longitude: Double?,
    val latitude: Double?,
    val connectedHubList: List<HubRouteResponseDto>
)

fun Hub.toResponseDto(connectedHubList: List<RouteResult>) = HubDetailResponseDto(
    name = name,
    address = address,
    longitude = longitude,
    latitude = latitude,
    connectedHubList = connectedHubList.map { it.toDto() }
)