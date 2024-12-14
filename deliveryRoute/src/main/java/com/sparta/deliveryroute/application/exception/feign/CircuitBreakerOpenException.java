package com.sparta.deliveryroute.application.exception.feign;

import com.sparta.deliveryroute.application.exception.Error;
import com.sparta.deliveryroute.application.exception.FeignException;

public class CircuitBreakerOpenException extends FeignException {
	public CircuitBreakerOpenException() {
		super(Error.CIRCUIT_BREAKER_OPEN);
	}
}
