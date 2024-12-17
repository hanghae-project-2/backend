package com.sparta.company.application.service

import com.sparta.company.application.client.HubService
import com.sparta.company.application.dto.request.BaseCompanyRequestDto
import com.sparta.company.application.dto.request.CompanySearchRequestDto
import com.sparta.company.application.dto.request.RegisterCompanyRequestDto
import com.sparta.company.application.dto.request.toEntity
import com.sparta.company.application.dto.response.CompanyResponseDto
import com.sparta.company.application.dto.response.CompanySummaryResponseDto
import com.sparta.company.application.dto.response.toResponseDto
import com.sparta.company.application.exception.AccessDeniedException
import com.sparta.company.application.exception.IncorrectHubIdException
import com.sparta.company.application.exception.NotFoundCompanyException
import com.sparta.company.application.exception.NotFoundHubException
import com.sparta.company.domain.repository.CompanyRepository
import jakarta.servlet.http.HttpServletRequest
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
    fun registerCompany(servletRequest: HttpServletRequest, request: RegisterCompanyRequestDto): UUID? {

        val createdBy = servletRequest.getHeader("X-Authenticated-User-Id")
        val role = servletRequest.getHeader("X-Authenticated-User-Role")

        if (role == null || (role != "HUB_ADMIN" && role != "MASTER")) {
            throw AccessDeniedException()
        }

        val hubId = extractUUID(request.hubId)

        if (role.equals("MASTER")) {
            hubService.existHub(hubId).takeIf { it } ?: throw NotFoundHubException()
        } else {
            hubService.existHubAndCheckManager(hubId, createdBy).takeIf { it } ?: throw NotFoundHubException()
        }

        return companyRepository.save(request.toEntity(createdBy)).id
    }

    @Transactional
    fun updateCompany(servletRequest: HttpServletRequest, companyId: UUID, request: BaseCompanyRequestDto): UUID? {

        val userId = servletRequest.getHeader("X-Authenticated-User-Id")
        val role = servletRequest.getHeader("X-Authenticated-User-Role")

        if (role == null || (role != "COMPANY_ADMIN" && role != "HUB_ADMIN" && role != "MASTER")) {
            throw AccessDeniedException()
        }


        val company = companyRepository.findByIdOrNull(companyId) ?: throw NotFoundCompanyException()

        when (role) {
            "MASTER" -> {
                hubService.existHub(company.hubId).takeIf { it } ?: throw NotFoundHubException()
            }

            "HUB_ADMIN" -> {
                hubService.existHubAndCheckManager(company.hubId, userId).takeIf { it } ?: throw NotFoundHubException()
            }

            "COMPANY_ADMIN" -> {
                company.checkManager(userId).takeIf { it } ?: throw AccessDeniedException()
                hubService.existHub(company.hubId).takeIf { it } ?: throw NotFoundHubException()
                request.isDelete.takeIf { it } ?: throw AccessDeniedException()
            }
        }

        company.updateInfo(
            request.name,
            request.type,
            request.address,
            request.manager,
            userId,
        )

        if (request.isDelete) {
            company.markAsDelete(userId)
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