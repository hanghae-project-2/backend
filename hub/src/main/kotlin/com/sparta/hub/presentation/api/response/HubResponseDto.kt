package com.sparta.hub.presentation.api.response

import com.sparta.hub.domain.model.Hub
import java.util.*

data class HubResponseDto(
    val id: UUID,
    val name: String,
    val address: String,
)

fun Hub.toDto() = HubResponseDto(
    id = id!!,
    name = name,
    address = address,
)