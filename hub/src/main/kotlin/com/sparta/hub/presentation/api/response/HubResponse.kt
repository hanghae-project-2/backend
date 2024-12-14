package com.sparta.hub.presentation.api.response

import com.sparta.hub.domain.model.Hub
import java.util.*

data class HubResponse(
    val hubId: UUID,
    val hubName: String,
)

fun Hub.toResponse() = HubResponse(
    hubId = this.id!!,
    hubName = this.name,
)