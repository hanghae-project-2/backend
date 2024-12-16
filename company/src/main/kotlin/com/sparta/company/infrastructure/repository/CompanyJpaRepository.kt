package com.sparta.company.infrastructure.repository

import com.querydsl.core.types.Order
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.Projections
import com.sparta.company.application.dto.request.CompanySearchRequestDto
import com.sparta.company.application.dto.response.CompanySummaryResponseDto
import com.sparta.company.domain.model.Company
import com.sparta.company.domain.model.CompanyType
import com.sparta.company.domain.model.QCompany.company
import com.sparta.company.domain.repository.CompanyRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository
import java.util.*

interface CompanyJpaRepository : JpaRepository<Company, UUID> {
    fun findByNameIs(name: String): Optional<Company>
}

@Repository
class CompanyRepositoryImpl(
    private val companyJpaRepository: CompanyJpaRepository
) : CompanyRepository, QuerydslRepositorySupport(Company::class.java) {

    override fun save(company: Company): Company {
        return companyJpaRepository.save(company)
    }

    override fun findByIdOrNull(id: UUID): Company? {
        return companyJpaRepository.findById(id).orElse(null)
    }

    override fun findByNameIs(name: String): Optional<Company> {
        return companyJpaRepository.findByNameIs(name)
    }

    override fun findByIds(ids: List<UUID>): List<Company> {
        return companyJpaRepository.findAllById(ids)
    }

    override fun findPageBy(
        pageRequest: Pageable,
        searchRequestDto: CompanySearchRequestDto
    ): Page<CompanySummaryResponseDto> {

        val orders: MutableList<OrderSpecifier<*>> = ArrayList()

        pageRequest.sort.forEach {
            when (it.property) {
                "name" -> orders.add(
                    OrderSpecifier(
                        if (it.isAscending) Order.ASC else Order.DESC,
                        company.name
                    )
                )

                "address" -> orders.add(
                    OrderSpecifier(
                        if (it.isAscending) Order.ASC else Order.DESC,
                        company.address
                    )
                )

                "createdBy" -> orders.add(
                    OrderSpecifier(
                        if (it.isAscending) Order.ASC else Order.DESC,
                        company.createdBy
                    )
                )

                "type" -> orders.add(
                    OrderSpecifier(
                        if (it.isAscending) Order.ASC else Order.DESC,
                        company.type
                    )
                )
            }
        }

        val result = from(company)
            .select(
                Projections.constructor(
                    CompanySummaryResponseDto::class.java,
                    company.id,
                    company.hubId,
                    company.name,
                    company.type.stringValue(),
                    company.address
                )
            )
            .where(
                searchRequestDto.name?.let { company.name.contains(it) },
                searchRequestDto.type?.let { company.type.eq(CompanyType.fromKey(it)) },
                searchRequestDto.address?.let { company.address.contains(it) },
            )
            .orderBy(*orders.toTypedArray())
            .offset(pageRequest.offset)
            .limit(pageRequest.pageSize.toLong())
            .fetchResults()

        return PageImpl(result.results, pageRequest, result.total)
    }
}