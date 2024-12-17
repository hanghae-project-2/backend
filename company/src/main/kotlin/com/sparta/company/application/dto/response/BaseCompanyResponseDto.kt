package com.sparta.company.application.dto.response

import com.sparta.company.domain.model.Company
import java.util.*

data class BaseCompanyResponseDto(
    val companyId: UUID,
    val companyName: String,
)

fun Company.toBaseResponseDto() = BaseCompanyResponseDto(
    companyId = this.id,
    companyName = this.name,
)