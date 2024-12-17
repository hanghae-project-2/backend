package com.sparta.deliveryroute.presentation.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record Response<T>(
		Integer code,
		String message,
		T data
) {
	public Response {
		if (code == null) {
			code = HttpStatus.OK.value();
		}
		if (message == null) {
			message = HttpStatus.OK.getReasonPhrase();
		}
	}
}