package com.sparta.order.presentation.api.handler;

import com.sparta.order.domain.exception.OrderException;
import com.sparta.order.presentation.api.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OrderException.class)
    public Response<Void> handleOrderException(OrderException ex) {
        return new Response<>(ex.getError().getStatus().value(), ex.getError().getMessage());
    }

}
