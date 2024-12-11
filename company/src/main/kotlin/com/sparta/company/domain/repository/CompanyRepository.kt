package com.sparta.company.domain.repository

import com.sparta.company.domain.model.Company

interface CompanyRepository {

    fun save(company: Company): Company
}