package com.sparta.deliveryroute.infrastructure.client;

import com.sparta.deliveryroute.application.client.HubService;
import com.sparta.deliveryroute.application.dto.RouteResult;
import com.sparta.deliveryroute.application.dto.response.HubRouteResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@FeignClient(
		name = "hub-server",
		fallbackFactory = HubFeignServiceFallbackFactory.class
)
public interface HubFeignService extends HubService {

	@Override
	@GetMapping("/hubs/routes/id")
	RouteResult findHubRoutesById(@RequestParam UUID startHubId, @RequestParam UUID endHubId);

	@Override
	@PostMapping("/hubs/routes")
	List<HubRouteResponseDto> findHubRoutesByHubRouteId(@RequestBody List<UUID> hubRouteIdList);

}
