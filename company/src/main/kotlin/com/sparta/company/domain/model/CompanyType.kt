package com.sparta.company.domain.model

enum class CompanyType(
    key: String,
    description: String,
) {
    PRODUCER("PRODUCER", "생산업체"),
    RECEIVER("RECEIVER", "수령업체"),
}