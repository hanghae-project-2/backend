package com.sparta.deliveryroute.infrastructure.client;

import com.sparta.deliveryroute.domain.client.HubClient;
import com.sparta.deliveryroute.infrastructure.client.response.RouteResult;
import com.sparta.deliveryroute.presentation.api.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient(
		name = "hub-server",
		fallbackFactory = HubFeignClientFallbackFactory.class
)
public interface HubFeignClient extends HubClient {

	@Override
	@GetMapping("/hubs/routes/id")
	Response<RouteResult> findHubRoutesById(@RequestParam UUID startHubId, @RequestParam UUID endHubId);

}
