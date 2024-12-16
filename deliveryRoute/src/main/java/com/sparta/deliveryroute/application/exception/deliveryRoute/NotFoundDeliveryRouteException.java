package com.sparta.deliveryroute.application.exception.deliveryRoute;

import com.sparta.deliveryroute.application.exception.DeliveryRouteException;
import com.sparta.deliveryroute.application.exception.Error;

public class NotFoundDeliveryRouteException extends DeliveryRouteException {
	public NotFoundDeliveryRouteException() {
		super(Error.NOT_FOUND_DELIVERY_ROUTE);
	}
}
