package com.sparta.hub.application.dto

data class RouteState(
    val nodeName: String,
    val cumulativeDistance: Int?,
    val cumulativeTime: Int?,
    val path: List<String>,
)
