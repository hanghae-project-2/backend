package com.sparta.hub.application.dto.request

import java.util.*

data class HubSearchRequestDto(
    val name: String? = null,
    val address: String? = null,
    val createdBy: UUID? = null,
)
