package com.sparta.deliveryroute.domain.exception.deliveryRoute;

import com.sparta.deliveryroute.domain.exception.DeliveryRouteException;
import com.sparta.deliveryroute.domain.exception.Error;

public class IncorrectDeliveryTypeException extends DeliveryRouteException {
	public IncorrectDeliveryTypeException() {
		super(Error.INCORRECT_DELIVERY_TYPE);
	}
}
