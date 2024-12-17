package com.sparta.hub.application.dto.response

import java.util.*

data class HubSummaryResponseDto(
    val id: UUID,
    val name: String,
    val address: String,
)