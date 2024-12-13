package com.sparta.hub.presentation.api.response

import com.sparta.hub.application.dto.RouteResult
import com.sparta.hub.application.dto.toDto
import com.sparta.hub.domain.model.Hub

data class HubDetailResponseDto(

    val name: String,
    val address: String,
    val longitude: Int?,
    val latitude: Int?,
    val connectedHubList: List<HubRouteResponseDto>

)

fun Hub.toResponseDto(connectedHubList: List<RouteResult>) = HubDetailResponseDto(
    name = name,
    address = address,
    longitude = longitude?.toInt(),
    latitude = latitude?.toInt(),
    connectedHubList = connectedHubList.map { it.toDto() }
)