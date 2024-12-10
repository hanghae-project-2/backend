package com.sparta.company.application.service

import com.sparta.company.application.dto.request.RegisterCompanyRequestDto
import com.sparta.company.application.dto.request.toEntity
import com.sparta.company.domain.client.HubClient
import com.sparta.company.domain.exception.IncorrectHubIdException
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

        val hubId = try {
            UUID.fromString(request.hubId)
        } catch (e: IllegalArgumentException) {
            throw IncorrectHubIdException()
        }

        hubClient.existHub(hubId).takeIf { it } ?: throw NotFoundHubException()

        return companyRepository.save(request.toEntity()).id!!
    }
}