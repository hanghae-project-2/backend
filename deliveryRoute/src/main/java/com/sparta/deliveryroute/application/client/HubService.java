package com.sparta.deliveryroute.application.client;

import com.sparta.deliveryroute.application.dto.RouteResult;

import java.util.UUID;

public interface HubService {

	RouteResult findHubRoutesById(UUID startHubId, UUID endHubId);

}
