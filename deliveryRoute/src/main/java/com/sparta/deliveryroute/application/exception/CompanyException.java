package com.sparta.deliveryroute.application.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CompanyException extends RuntimeException {

	Error error;

	public CompanyException(Error error) {
		this.error = error;
	}
}
