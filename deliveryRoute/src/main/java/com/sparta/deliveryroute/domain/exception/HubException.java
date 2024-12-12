package com.sparta.deliveryroute.domain.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HubException extends RuntimeException {

	Error error;

	public HubException(Error error) {
		this.error = error;
	}
}
