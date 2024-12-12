package com.sparta.company.presentation.api.controller

import com.sparta.company.domain.service.CompanyService
import com.sparta.company.presentation.api.controller.docs.CompanyControllerDocs
import com.sparta.company.presentation.api.request.BaseCompanyRequest
import com.sparta.company.presentation.api.request.RegisterCompanyRequest
import com.sparta.company.presentation.api.request.toDto
import com.sparta.company.presentation.api.response.BaseCompanyResponse
import com.sparta.company.presentation.api.response.Response
import com.sparta.company.presentation.api.response.toResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/companies")
class CompanyController(
    private val companyService: CompanyService
) : CompanyControllerDocs() {

    @PostMapping
    override fun registerCompany(
        @RequestBody request: RegisterCompanyRequest
    ): Response<UUID> =
        Response(
            HttpStatus.CREATED.value(),
            HttpStatus.CREATED.reasonPhrase,
            companyService.registerCompany(request.toDto())
        )

    @PatchMapping("/{companyId}")
    override fun updateCompany(
        @PathVariable companyId: UUID,
        @RequestBody request: BaseCompanyRequest
    ): Response<UUID> =
        Response(
            HttpStatus.OK.value(),
            HttpStatus.OK.reasonPhrase,
            companyService.updateCompany(companyId, request.toDto())
        )

    @GetMapping("/{companyId}")
    override fun getCompany(
        @PathVariable companyId: UUID
    ): Response<BaseCompanyResponse> =
        Response(
            HttpStatus.OK.value(),
            HttpStatus.OK.reasonPhrase,
            companyService.getCompany(companyId).toResponse()
        )
}