package com.sparta.company.application.dto.response

import java.util.*

data class CompanySummaryResponseDto(
    val id: UUID,
    val hubId: UUID,
    val name: String,
    val type: String,
    val address: String,
)