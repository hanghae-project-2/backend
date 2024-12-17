package com.sparta.hub.application.dto.request

data class HubRequestDto(
    val name: String,
    val address: String,
    val manager: String,
    val isDelete: Boolean,
)