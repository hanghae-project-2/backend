package com.sparta.hub.application.dto

import com.sparta.hub.domain.model.HubRoute

data class RouteInfo(
    val destination: String?,
    val distance: Int?,
    val time: Int?,
)

fun HubRoute.toRouteInfo() = RouteInfo(
    destination = endHub?.name,
    distance = estimatedMeter,
    time = estimatedSecond,
)