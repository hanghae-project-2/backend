package com.sparta.company.application.dto.request

data class BaseCompanyRequestDto(
    val name: String,
    val type: String,
    val address: String,
    val manager: String,
    val isDelete: Boolean,
)
