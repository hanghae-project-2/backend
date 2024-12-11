package com.sparta.company.application.dto.response

import com.sparta.company.domain.model.Company
import com.sparta.company.domain.model.CompanyType

data class BaseCompanyResponseDto(
    val name: String,
    val address: String,
    val type: String,
    val hubId: String,
)

fun Company.toResponseDto() = BaseCompanyResponseDto(
    name = name,
    address = address,
    type = CompanyType.toKey(type),
    hubId = hubId.toString(),
)