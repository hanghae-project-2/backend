package com.sparta.company.application.service

import com.sparta.company.application.client.HubService
import com.sparta.company.application.dto.request.BaseCompanyRequestDto
import com.sparta.company.application.dto.request.CompanySearchRequestDto
import com.sparta.company.application.dto.request.RegisterCompanyRequestDto
import com.sparta.company.application.dto.request.toEntity
import com.sparta.company.application.dto.response.CompanyResponseDto
import com.sparta.company.application.dto.response.CompanySummaryResponseDto
import com.sparta.company.application.dto.response.toResponseDto
import com.sparta.company.application.exception.IncorrectHubIdException
import com.sparta.company.application.exception.NotFoundCompanyException
import com.sparta.company.application.exception.NotFoundHubException
import com.sparta.company.domain.repository.CompanyRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class CompanyService(
    private val hubService: HubService,
    private val companyRepository: CompanyRepository,
) {

    @Transactional
    fun registerCompany(request: RegisterCompanyRequestDto): UUID {

        val hubId = extractUUID(request.hubId)

        hubService.existHub(hubId).takeIf { it } ?: throw NotFoundHubException()

        return companyRepository.save(request.toEntity()).id
    }

    @Transactional
    fun updateCompany(companyId: UUID, request: BaseCompanyRequestDto): UUID {

        val company = companyRepository.findByIdOrNull(companyId) ?: throw NotFoundCompanyException()

        company.updateInfo(request.name, request.type, request.address)

        if (request.isDelete) {
            company.markAsDelete()
        }

        return companyRepository.save(company).id
    }

    @Transactional(readOnly = true)
    fun getCompany(companyId: UUID): CompanyResponseDto {
        return companyRepository.findByIdOrNull(companyId)?.toResponseDto() ?: throw NotFoundCompanyException()
    }

    private fun extractUUID(target: String): UUID =
        try {
            UUID.fromString(target)
        } catch (e: IllegalArgumentException) {
            throw IncorrectHubIdException()
        }

    @Transactional(readOnly = true)
    fun searchCompany(
        pageable: Pageable,
        requestDto: CompanySearchRequestDto
    ): Page<CompanySummaryResponseDto> {
        return companyRepository.findPageBy(pageable, requestDto)
    }

    @Transactional(readOnly = true)
    fun findCompanyByName(
        name: String
    ): CompanyResponseDto {
        return companyRepository.findByNameIs(name).orElseThrow { NotFoundCompanyException() }.toResponseDto()
    }

    @Transactional(readOnly = true)
    fun findCompanyById(
        id: UUID
    ): CompanyResponseDto {
        return companyRepository.findByIdOrNull(id)?.toResponseDto() ?: throw NotFoundCompanyException()
    }

    @Transactional(readOnly = true)
    fun findCompaniesByIds(
        ids: List<UUID>
    ): List<CompanyResponseDto> {
        return companyRepository.findByIds(ids).map { it.toResponseDto() }
    }
}