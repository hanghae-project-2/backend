package com.sparta.hub.application.dto.request

// 계층간의 확실한 분리는 좋음, 지금 상황에서는 굳이?
data class HubRequestDto(
    val name: String,
    val address: String,
    val isDelete: Boolean,
)