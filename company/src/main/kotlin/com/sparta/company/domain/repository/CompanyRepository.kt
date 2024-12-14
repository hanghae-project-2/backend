package com.sparta.company.domain.repository

import com.sparta.company.application.dto.request.CompanySearchRequestDto
import com.sparta.company.application.dto.response.CompanySummaryResponseDto
import com.sparta.company.domain.model.Company
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface CompanyRepository {

    fun save(company: Company): Company

    fun findByIdOrNull(id: UUID): Company?

    fun findByNameIs(name: String): Optional<Company>

    fun findByIds(ids: List<UUID>): List<Company>

    fun findPageBy(pageRequest: Pageable, searchRequestDto: CompanySearchRequestDto): Page<CompanySummaryResponseDto>
}