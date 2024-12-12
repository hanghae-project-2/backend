package com.sparta.company.domain.repository

import com.sparta.company.domain.model.Company
import java.util.*

interface CompanyRepository {

    fun save(company: Company): Company

    fun findByIdOrNull(id: UUID): Company?
}