package com.sparta.deliveryroute.application.client;

import com.sparta.deliveryroute.application.dto.response.RouteResult;
import com.sparta.deliveryroute.presentation.api.response.Response;

import java.util.UUID;

public interface HubService {

	Response<RouteResult> findHubRoutesById(UUID startHubId, UUID endHubId);

}
