package com.sparta.company.presentation.api.controller.docs

import com.sparta.company.presentation.api.request.RegisterCompanyRequest
import com.sparta.company.presentation.api.response.Response
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import java.util.*

@Tag(name = "Company", description = "업체 API")
abstract class CompanyControllerDocs {

    @Operation(summary = "업체 등록", description = "업체를 등록하는 API 입니다.")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201",
                description = "업체 등록 성공",
                content = [Content(schema = Schema(implementation = Response::class))]
            )
        ]
    )
    @PostMapping("/companies")
    abstract fun registerCompany(
        @RequestBody request: RegisterCompanyRequest
    ): Response<UUID>

}