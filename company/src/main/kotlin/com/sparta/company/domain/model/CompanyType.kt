package com.sparta.company.domain.model

import com.sparta.company.domain.exception.IncorrectCompanyTypeException

enum class CompanyType(
    val key: String,
    val description: String,
) {
    PRODUCER("PRODUCER", "생산업체"),
    RECEIVER("RECEIVER", "수령업체");

    companion object {
        fun fromKey(key: String): CompanyType {
            return entries.find { it.key == key } ?: throw IncorrectCompanyTypeException()
        }
    }
}