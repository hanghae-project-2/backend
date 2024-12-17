package com.sparta.deliveryroute.application.exception.feign;

import com.sparta.deliveryroute.application.exception.Error;
import com.sparta.deliveryroute.application.exception.FeignException;

public class ServerTimeoutException extends FeignException {
	public ServerTimeoutException() {
		super(Error.SERVER_TIMEOUT);
	}
}
