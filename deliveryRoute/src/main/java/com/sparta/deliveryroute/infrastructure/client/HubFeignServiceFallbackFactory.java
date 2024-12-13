package com.sparta.deliveryroute.infrastructure.client;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class HubFeignServiceFallbackFactory implements FallbackFactory<HubFeignService> {
	@Override
	public HubFeignService create(Throwable cause) {
		return null;
	}
}
