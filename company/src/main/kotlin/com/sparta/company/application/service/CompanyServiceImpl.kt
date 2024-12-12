package com.sparta.company.application.service

import com.sparta.company.application.dto.request.BaseCompanyRequestDto
import com.sparta.company.application.dto.request.RegisterCompanyRequestDto
import com.sparta.company.application.dto.request.toEntity
import com.sparta.company.application.dto.response.BaseCompanyResponseDto
import com.sparta.company.application.dto.response.toResponseDto
import com.sparta.company.domain.client.HubClient
import com.sparta.company.domain.exception.IncorrectHubIdException
import com.sparta.company.domain.exception.NotFoundCompanyException
import com.sparta.company.domain.exception.NotFoundHubException
import com.sparta.company.domain.repository.CompanyRepository
import com.sparta.company.domain.service.CompanyService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class CompanyServiceImpl(
    private val hubClient: HubClient,
    private val companyRepository: CompanyRepository,
) : CompanyService {

    @Transactional
    override fun registerCompany(request: RegisterCompanyRequestDto): UUID {

        val hubId = extractUUID(request.hubId)

        hubClient.existHub(hubId).takeIf { it } ?: throw NotFoundHubException()

        return companyRepository.save(request.toEntity()).id
    }

    @Transactional
    override fun updateCompany(companyId: UUID, request: BaseCompanyRequestDto): UUID {

        val company = companyRepository.findByIdOrNull(companyId) ?: throw NotFoundCompanyException()

        company.updateInfo(request.name, request.type, request.address)

        if (request.isDelete) {
            company.markAsDelete()
        }

        return companyRepository.save(company).id
    }

    //TODO: 업체 조회 시 추가데이터 필요할지?
    @Transactional(readOnly = true)
    override fun getCompany(companyId: UUID): BaseCompanyResponseDto {
        return companyRepository.findByIdOrNull(companyId)?.toResponseDto() ?: throw NotFoundCompanyException()
    }

    private fun extractUUID(target: String): UUID =
        try {
            UUID.fromString(target)
        } catch (e: IllegalArgumentException) {
            throw IncorrectHubIdException()
        }
}