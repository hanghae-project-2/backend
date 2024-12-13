package com.sparta.company.presentation.api.request

import com.sparta.company.application.dto.request.BaseCompanyRequestDto

data class BaseCompanyRequest(
    val name: String,
    val type: String,
    val address: String,
    val isDelete: Boolean,
)

fun BaseCompanyRequest.toDto() = BaseCompanyRequestDto(
    name = name,
    type = type,
    address = address,
    isDelete = isDelete,
)