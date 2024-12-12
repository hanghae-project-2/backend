package com.sparta.deliveryroute.application.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FeignException extends RuntimeException {

	Error error;

	public FeignException(Error error) {
		this.error = error;
	}

}
