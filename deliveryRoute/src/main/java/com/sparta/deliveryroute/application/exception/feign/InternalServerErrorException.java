package com.sparta.deliveryroute.application.exception.feign;

import com.sparta.deliveryroute.application.exception.Error;
import com.sparta.deliveryroute.application.exception.FeignException;

public class InternalServerErrorException extends FeignException {
	public InternalServerErrorException() {
		super(Error.INTERNAL_SERVER_ERROR);
	}
}
