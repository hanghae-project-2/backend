package com.sparta.deliveryroute.infrastructure.client;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class HubFeignClientFallbackFactory implements FallbackFactory<HubFeignClient> {
	@Override
	public HubFeignClient create(Throwable cause) {
		return null;
	}
}
