package com.sparta.hub.presentation.api.response

import com.sparta.hub.application.dto.response.HubResponseDto
import java.util.*

data class HubResponse(
    val hubId: UUID,
    val hubName: String,
)

fun HubResponseDto.toResponse() = HubResponse(
    hubId = this.hubId,
    hubName = this.hubName,
)