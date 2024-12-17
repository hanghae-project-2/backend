package com.sparta.company.presentation.api.response

import com.sparta.company.application.dto.response.CompanyResponseDto

data class CompanyResponse(
    val name: String,
    val address: String,
    val type: String,
    val hubId: String,
)

fun CompanyResponseDto.toResponse() = CompanyResponse(
    name = name,
    address = address,
    type = type,
    hubId = hubId,
)