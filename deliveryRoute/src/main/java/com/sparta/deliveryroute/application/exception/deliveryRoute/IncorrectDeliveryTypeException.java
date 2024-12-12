package com.sparta.deliveryroute.application.exception.deliveryRoute;

import com.sparta.deliveryroute.application.exception.DeliveryRouteException;
import com.sparta.deliveryroute.application.exception.Error;

public class IncorrectDeliveryTypeException extends DeliveryRouteException {
	public IncorrectDeliveryTypeException() {
		super(Error.INCORRECT_DELIVERY_TYPE);
	}
}
