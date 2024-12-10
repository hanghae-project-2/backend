package com.sparta.company.infrastructure.repository

import com.sparta.company.domain.model.Company
import com.sparta.company.domain.repository.CompanyRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

interface CompanyJpaRepository : JpaRepository<Company, UUID>

@Repository
class CompanyRepositoryImpl(
    private val companyJpaRepository: CompanyJpaRepository
) : CompanyRepository {

    override fun save(company: Company): Company {
        return companyJpaRepository.save(company)
    }
}