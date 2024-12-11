package com.sparta.company.application.dto.request

data class CompanySearchRequestDto(
    val name: String? = null,
    val type: String? = null,
    val address: String? = null,
)
