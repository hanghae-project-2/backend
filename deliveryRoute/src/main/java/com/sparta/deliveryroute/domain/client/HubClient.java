package com.sparta.deliveryroute.domain.client;

import com.sparta.deliveryroute.infrastructure.client.response.RouteResult;
import com.sparta.deliveryroute.presentation.api.response.Response;

import java.util.UUID;

public interface HubClient {

	Response<RouteResult> findHubRoutesById(UUID startHubId, UUID endHubId);

}
