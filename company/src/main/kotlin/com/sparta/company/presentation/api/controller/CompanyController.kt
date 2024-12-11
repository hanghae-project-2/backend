package com.sparta.company.presentation.api.controller

import com.sparta.company.domain.service.CompanyService
import com.sparta.company.presentation.api.controller.docs.CompanyControllerDocs
import com.sparta.company.presentation.api.request.RegisterCompanyRequest
import com.sparta.company.presentation.api.request.toDto
import com.sparta.company.presentation.api.response.Response
import org.springframework.http.HttpStatus
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
    override fun registerCompany(@RequestBody request: RegisterCompanyRequest): Response<UUID> =
        Response(
            HttpStatus.CREATED.value(),
            HttpStatus.CREATED.reasonPhrase,
            companyService.registerCompany(request.toDto())
        )
}