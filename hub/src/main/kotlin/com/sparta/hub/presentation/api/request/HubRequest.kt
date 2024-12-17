package com.sparta.hub.presentation.api.request

import com.sparta.hub.application.dto.request.HubRequestDto

data class HubRequest(
    val name: String,
    val address: String,
    val manager: String,
    val isDelete: Boolean,
)

fun HubRequest.toDto() = HubRequestDto(
    name = name,
    address = address,
    manager = manager,
    isDelete = isDelete,
)