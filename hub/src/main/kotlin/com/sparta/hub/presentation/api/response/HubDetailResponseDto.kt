package com.sparta.hub.presentation.api.response

data class HubDetailResponseDto(

    val name: String,
    val address: String,
    val longitude: Int?,
    val latitude: Int?,
    val connectedHubList: List<HubRouteResponseDto>

)