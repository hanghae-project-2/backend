package com.sparta.company.presentation.api.handler

import com.sparta.company.domain.exception.CompanyException
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

}