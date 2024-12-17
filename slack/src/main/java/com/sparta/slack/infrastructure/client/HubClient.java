package com.sparta.slack.infrastructure.client;

import com.sparta.slack.application.dto.HubDetails;
import com.sparta.slack.application.dto.HubInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient(name = "hub-service", url = "${hub.service.url}")
public interface HubClient {

    @GetMapping("hubs/routes/id")
    HubDetails.Response findHubRoutesByName(
            @RequestParam("startHubName") UUID startHubId,
            @RequestParam("endHubName") UUID endHubId
    );

    @GetMapping("/hubs/find/{hubId}")
    HubInfo.Response findHubById(
            @PathVariable UUID hubId
    );


}