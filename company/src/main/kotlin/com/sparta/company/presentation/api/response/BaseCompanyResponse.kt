package com.sparta.company.presentation.api.response

import com.sparta.company.application.dto.response.BaseCompanyResponseDto

data class BaseCompanyResponse(
    val name: String,
    val address: String,
    val type: String,
    val hubId: String,
)

fun BaseCompanyResponseDto.toResponse() = BaseCompanyResponse(
    name = name,
    address = address,
    type = type,
    hubId = hubId,
)