package com.sparta.company.presentation.api.request

import com.sparta.company.application.dto.request.CompanySearchRequestDto
import org.springframework.web.bind.annotation.RequestParam

data class CompanySearchRequest(
    @RequestParam(required = false)
    val name: String?,
    @RequestParam(required = false)
    val type: String?,
    @RequestParam(required = false)
    val address: String?,
)

fun CompanySearchRequest.toDto() = CompanySearchRequestDto(
    name = name,
    type = type,
    address = address,
)
