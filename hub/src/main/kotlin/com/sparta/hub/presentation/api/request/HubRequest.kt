package com.sparta.hub.presentation.api.request

import com.sparta.hub.application.dto.request.HubRequestDto

data class HubRequest(
    val name: String,
    val address: String,
    val isDelete: Boolean,
)

fun HubRequest.toDto() = HubRequestDto(
    name = name,
    address = address,
    isDelete = isDelete,
)