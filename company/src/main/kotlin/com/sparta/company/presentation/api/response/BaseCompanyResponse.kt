package com.sparta.company.presentation.api.response

import com.sparta.company.application.dto.response.BaseCompanyResponseDto
import java.util.*

data class BaseCompanyResponse(
    val companyId: UUID,
    val companyName: String,
)

fun BaseCompanyResponseDto.toResponse() = BaseCompanyResponse(
    companyId = this.companyId,
    companyName = this.companyName,
)