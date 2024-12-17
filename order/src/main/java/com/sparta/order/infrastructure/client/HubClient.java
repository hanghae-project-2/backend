package com.sparta.order.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "hub-server")
public interface HubClient {
    @GetMapping("/hubs/find/user/{userId}")
    UUID findHubByUserId(@PathVariable UUID userId);
}
