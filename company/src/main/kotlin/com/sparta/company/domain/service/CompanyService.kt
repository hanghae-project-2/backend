package com.sparta.company.domain.service

import com.sparta.company.application.dto.request.BaseCompanyRequestDto
import com.sparta.company.application.dto.request.RegisterCompanyRequestDto
import com.sparta.company.application.dto.response.BaseCompanyResponseDto
import java.util.*

interface CompanyService {

    fun registerCompany(request: RegisterCompanyRequestDto): UUID

    fun updateCompany(companyId: UUID, request: BaseCompanyRequestDto): UUID

    fun getCompany(companyId: UUID): BaseCompanyResponseDto
}