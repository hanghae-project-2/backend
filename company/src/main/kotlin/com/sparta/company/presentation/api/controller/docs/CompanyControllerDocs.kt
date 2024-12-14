package com.sparta.company.presentation.api.controller.docs

import com.sparta.company.presentation.api.request.BaseCompanyRequest
import com.sparta.company.presentation.api.request.CompanySearchRequest
import com.sparta.company.presentation.api.request.RegisterCompanyRequest
import com.sparta.company.presentation.api.response.CompanyResponse
import com.sparta.company.presentation.api.response.CompanySummaryResponse
import com.sparta.company.presentation.api.response.Response
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
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
            ),
            ApiResponse(
                responseCode = "400",
                description = """
                | 1. 잘못된 허브 ID 입니다.
                | - hubId가 UUID 형식이 아닌 경우
                | 2. 잘못된 업체 타입입니다.
                | - companyType 이 PRODUCER 혹은 RECEIVER 가 아닌 경우
                | 3. 서비스가 일시적으로 사용 불가능합니다.
                | - Circuit Breaker 가 OPEN 된 경우
                | 4. 응답 시간을 초과하였습니다.
                | - 3번 이상의 재시도 후에도 응답이 없는 경우
                | 5. 서버 내부 오류가 발생하였습니다.
                | - 그 외의 다른 이유로 서버 내부 오류가 발생한 경우
            """,
                content = [Content(schema = Schema(implementation = Response::class))]
            ),
            ApiResponse(
                responseCode = "404",
                description = """
                | 1. 허브를 찾을 수 없습니다.
                | - Hub 서버와 통신이 불가능한 경우
                | - hubId에 해당하는 Hub가 존재하지 않는 경우
            """,
                content = [Content(schema = Schema(implementation = Response::class))]
            )
        ]
    )
    @PostMapping("/companies")
    abstract fun registerCompany(
        @RequestBody request: RegisterCompanyRequest
    ): Response<UUID>

    @Operation(summary = "업체 정보 수정", description = "업체 정보를 수정하는 API 입니다.")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "업체 정보 수정 성공",
                content = [Content(schema = Schema(implementation = Response::class))]
            ),
            ApiResponse(
                responseCode = "400",
                description = """
                | 1. 잘못된 업체 타입입니다.
                | - companyType 이 PRODUCER 혹은 RECEIVER 가 아닌 경우
                """,
                content = [Content(schema = Schema(implementation = Response::class))]
            ),
            ApiResponse(
                responseCode = "404",
                description = """
                | 1. 업체를 찾을 수 없습니다.
                | - companyId에 해당하는 업체가 존재하지 않는 경우
            """,
                content = [Content(schema = Schema(implementation = Response::class))]
            )
        ]
    )
    @PatchMapping("/companies/{companyId}")
    abstract fun updateCompany(
        @PathVariable companyId: UUID,
        @RequestBody request: BaseCompanyRequest
    ): Response<UUID>

    @Operation(summary = "단일 업체 조회", description = "단일 업체를 조회하는 API 입니다.")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "단일 업체 조회 성공",
                content = [Content(schema = Schema(implementation = Response::class))]
            ),
            ApiResponse(
                responseCode = "404",
                description = """
                | 1. 업체를 찾을 수 없습니다.
                | - companyId에 해당하는 업체가 존재하지 않는 경우
            """,
                content = [Content(schema = Schema(implementation = Response::class))]
            )
        ]
    )
    @GetMapping("/companies/{companyId}")
    abstract fun getCompany(
        @PathVariable companyId: UUID
    ): Response<CompanyResponse>

    @Operation(summary = "업체 검색", description = "업체를 검색하는 API 입니다.")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "업체 검색 성공",
                content = [Content(schema = Schema(implementation = Response::class))]
            )
        ]
    )
    @GetMapping("/companies/search")
    abstract fun searchCompany(
        pageable: Pageable,
        request: CompanySearchRequest
    ): Response<Page<CompanySummaryResponse>>
}