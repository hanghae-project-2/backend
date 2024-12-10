package com.sparta.company.application.dto.request

import com.sparta.company.domain.model.Company
import com.sparta.company.domain.model.CompanyType
import java.util.*

data class RegisterCompanyRequestDto(
    val name: String,
    val type: String,
    val address: String,
    val hubId: String,
)

fun RegisterCompanyRequestDto.toEntity(): Company = Company(
    name = name,
    type = CompanyType.fromKey(type),
    address = address,
    hubId = UUID.fromString(hubId)
)