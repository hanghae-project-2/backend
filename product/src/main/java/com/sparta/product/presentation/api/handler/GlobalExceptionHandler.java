package com.sparta.product.presentation.api.handler;

import com.sparta.product.domain.exception.Error;
import com.sparta.product.domain.exception.ProductNullPointerException;
import com.sparta.product.presentation.api.response.ApiResponse;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ProductNullPointerException.class)
    public ApiResponse<ErrorResponse> handleNullPointerException(ProductNullPointerException ex) {
        return buildErrorResponse(Error.NOT_FOUND_PRODUCT);
    }


    private ApiResponse<ErrorResponse> buildErrorResponse(Error error) {
        return new ApiResponse<>(error.getCode(), error.getMessage());
    }
}
