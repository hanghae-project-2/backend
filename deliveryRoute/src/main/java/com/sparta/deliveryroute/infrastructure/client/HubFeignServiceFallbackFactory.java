package com.sparta.deliveryroute.infrastructure.client;

import com.sparta.deliveryroute.application.dto.RouteResult;
import com.sparta.deliveryroute.application.dto.response.HubRouteResponseDto;
import com.sparta.deliveryroute.application.exception.feign.CircuitBreakerOpenException;
import com.sparta.deliveryroute.application.exception.feign.InternalServerErrorException;
import com.sparta.deliveryroute.application.exception.feign.ServerTimeoutException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

@Component
public class HubFeignServiceFallbackFactory implements FallbackFactory<HubFeignService> {
	@Override
	public HubFeignService create(Throwable cause) {
		return new HubFeignService() {
			@Override
			public RouteResult findHubRoutesById(UUID startHubId, UUID endHubId) {
				if (cause instanceof CallNotPermittedException) {
					throw new CircuitBreakerOpenException();
				} else if (cause instanceof TimeoutException) {
					throw new ServerTimeoutException();
				} else {
					throw new InternalServerErrorException();
				}
			}

			@Override
			public List<HubRouteResponseDto> findHubRoutesByHubRouteId(List<UUID> hubRouteIdList) {
				if (cause instanceof CallNotPermittedException) {
					throw new CircuitBreakerOpenException();
				} else if (cause instanceof TimeoutException) {
					throw new ServerTimeoutException();
				} else {
					throw new InternalServerErrorException();
				}
			}
		};
	}
}
