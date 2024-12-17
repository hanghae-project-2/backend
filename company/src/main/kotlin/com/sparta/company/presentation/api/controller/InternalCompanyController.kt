package com.sparta.company.presentation.api.controller

import com.sparta.company.application.service.CompanyService
import com.sparta.company.presentation.api.response.CompanyResponse
import com.sparta.company.presentation.api.response.toResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class InternalCompanyController(
    private val companyService: CompanyService
) {

    @GetMapping("/companies/search-by-name")
    fun findCompanyByName(
        @RequestParam name: String
    ): CompanyResponse =
        companyService.findCompanyByName(name).toResponse()

    @GetMapping("/companies/find/{companyId}")
    fun findCompanyById(
        @PathVariable companyId: UUID
    ): CompanyResponse =
        companyService.findCompanyById(companyId).toResponse()

    @PostMapping("/companies/batch")
    fun findCompaniesByIds(
        @RequestBody ids: List<UUID>
    ): List<CompanyResponse> =
        companyService.findCompaniesByIds(ids).map { it.toResponse() }

}