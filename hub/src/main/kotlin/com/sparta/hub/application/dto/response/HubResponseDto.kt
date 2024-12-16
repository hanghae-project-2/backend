package com.sparta.hub.application.dto.response

import com.sparta.hub.domain.model.Hub
import java.util.*

data class HubResponseDto(
    val hubId: UUID,
    val hubName: String,
)

fun Hub.toResponseDto() = HubResponseDto(
    hubId = this.id!!,
    hubName = this.name,
)