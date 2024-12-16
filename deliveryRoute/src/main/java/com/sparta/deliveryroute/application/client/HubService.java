package com.sparta.deliveryroute.application.client;

import com.sparta.deliveryroute.application.dto.RouteResult;
import com.sparta.deliveryroute.application.dto.response.HubRouteResponseDto;

import java.util.List;
import java.util.UUID;

public interface HubService {

	RouteResult findHubRoutesById(UUID startHubId, UUID endHubId);

	List<HubRouteResponseDto> findHubRoutesByHubRouteId(List<UUID> hubRouteIdList);
}
