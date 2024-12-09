package com.sparta.hub.application.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class RouteResult(
    @JsonProperty("distance")
    val distance: Int?,
    @JsonProperty("time")
    val time: Int?,
    @JsonProperty("path")
    val path: List<String>,
)