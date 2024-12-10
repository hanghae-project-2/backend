package com.sparta.company.presentation.api.controller

import com.sparta.company.domain.service.CompanyService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/companies")
class CompanyController(
    private val companyService: CompanyService
)