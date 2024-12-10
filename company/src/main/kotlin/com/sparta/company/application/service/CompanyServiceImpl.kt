package com.sparta.company.application.service

import com.sparta.company.domain.repository.CompanyRepository
import com.sparta.company.domain.service.CompanyService
import org.springframework.stereotype.Service

@Service
class CompanyServiceImpl(
    private val companyRepository: CompanyRepository
) : CompanyService