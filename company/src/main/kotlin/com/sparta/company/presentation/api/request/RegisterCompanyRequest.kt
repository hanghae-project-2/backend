package com.sparta.company.presentation.api.request

import com.sparta.company.application.dto.request.RegisterCompanyRequestDto

data class RegisterCompanyRequest(
    val name: String,
    val type: String,
    val address: String,
    val hubId: String,
)

fun RegisterCompanyRequest.toDto() = RegisterCompanyRequestDto(
    name = name,
    type = type,
    address = address,
    hubId = hubId
)