package com.sparta.hub.presentation.api.handler

import com.sparta.hub.domain.exception.HubException
import com.sparta.hub.presentation.api.response.Response
import io.swagger.v3.oas.annotations.Hidden
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@Hidden
@RestControllerAdvice
class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HubException::class)
    fun handleHubException(ex: HubException): Response<Unit> {
        return Response(ex.error.status.value(), ex.error.message)
    }
    
}