package com.sparta.company.domain.service

import com.sparta.company.application.dto.request.RegisterCompanyRequestDto
import java.util.*

interface CompanyService {

    fun registerCompany(request: RegisterCompanyRequestDto): UUID
}