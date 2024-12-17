package com.sparta.hub.presentation.api.response

import com.sparta.hub.application.dto.response.HubSummaryResponseDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import java.util.*

data class HubSummaryResponse(
    val id: UUID,
    val name: String,
    val address: String,
)

fun Page<HubSummaryResponseDto>.toResponse() = PageImpl(
    content.map { it.toResponse() },
    pageable,
    totalElements
)

fun HubSummaryResponseDto.toResponse() = HubSummaryResponse(
    id = id,
    name = name,
    address = address,
)