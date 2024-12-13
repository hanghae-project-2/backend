package com.sparta.company.presentation.api.response

import com.sparta.company.application.dto.response.CompanySummaryResponseDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import java.util.*

data class CompanySummaryResponse(
    val id: UUID,
    val hubId: UUID,
    val name: String,
    val type: String,
    val address: String,
)

fun Page<CompanySummaryResponseDto>.toResponse() = PageImpl(
    content.map { it.toResponse() },
    pageable,
    totalElements
)

fun CompanySummaryResponseDto.toResponse() = CompanySummaryResponse(
    id = id,
    hubId = hubId,
    name = name,
    type = type,
    address = address,
)