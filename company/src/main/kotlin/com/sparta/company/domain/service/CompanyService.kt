package com.sparta.company.domain.service

import com.sparta.company.application.dto.request.BaseCompanyRequestDto
import com.sparta.company.application.dto.request.CompanySearchRequestDto
import com.sparta.company.application.dto.request.RegisterCompanyRequestDto
import com.sparta.company.application.dto.response.BaseCompanyResponseDto
import com.sparta.company.application.dto.response.CompanySummaryResponseDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface CompanyService {

    fun registerCompany(request: RegisterCompanyRequestDto): UUID

    fun updateCompany(companyId: UUID, request: BaseCompanyRequestDto): UUID

    fun getCompany(companyId: UUID): BaseCompanyResponseDto

    fun searchCompany(pageable: Pageable, requestDto: CompanySearchRequestDto): Page<CompanySummaryResponseDto>
}