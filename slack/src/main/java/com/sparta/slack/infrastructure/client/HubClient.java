package com.sparta.slack.infrastructure.client;

import com.sparta.slack.infrastructure.dto.HubOptimizeApi;
import com.sparta.slack.infrastructure.dto.UserDetails;
import com.sparta.slack.presentation.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient(name = "hub-service", url = "${hub.service.url}")
public interface HubClient {

    @GetMapping("/routes/name")
    HubOptimizeApi.Response findHubRoutesByName(
            @RequestParam("startHubName") UUID startHubId,
            @RequestParam("endHubName") UUID endHubId
    );


}