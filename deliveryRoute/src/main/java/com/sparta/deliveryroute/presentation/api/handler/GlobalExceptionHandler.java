package com.sparta.deliveryroute.presentation.api.handler;

import com.sparta.deliveryroute.application.exception.DeliveryRouteException;
import com.sparta.deliveryroute.application.exception.Error;
import com.sparta.deliveryroute.application.exception.FeignException;
import com.sparta.deliveryroute.presentation.api.response.Response;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DeliveryRouteException.class)
	public Response<Void> deliveryRouteExceptionHandler(DeliveryRouteException e) {
		Error error = e.getError();

		return new Response<>(
				error.getHttpStatus().value(),
				error.getMessage(),
				null
		);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(FeignException.class)
	public Response<Void> feignExceptionHandler(FeignException e) {
		Error error = e.getError();

		return new Response<>(
				error.getHttpStatus().value(),
				error.getMessage(),
				null
		);
	}
}
