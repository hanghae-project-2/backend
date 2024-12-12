package com.sparta.company.presentation.api.handler

import com.sparta.company.application.exception.CompanyException
import com.sparta.company.application.exception.FeignException
import com.sparta.company.application.exception.HubException
import com.sparta.company.presentation.api.response.Response
import io.swagger.v3.oas.annotations.Hidden
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@Hidden
@RestControllerAdvice
class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CompanyException::class)
    fun handleCompanyException(ex: CompanyException): Response<Unit> {
        return Response(ex.error.status.value(), ex.error.message)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HubException::class)
    fun handleHubException(ex: HubException): Response<Unit> {
        return Response(ex.error.status.value(), ex.error.message)
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(FeignException::class)
    fun handleFeignException(ex: FeignException): Response<Unit> {
        return Response(ex.error.status.value(), ex.error.message)
    }

}