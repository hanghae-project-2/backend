package com.sparta.delivery.infrastructure.client;

import com.sparta.delivery.application.dto.response.HubResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "hub-server")
public interface HubClient {
	@GetMapping("/hubs/search-by-name")
	List<HubResponseDto> findHubsByName(@RequestParam("name") String name);

    @GetMapping("/hubs/find/{hubId}")
    HubResponseDto findHubById(@PathVariable("hubId") UUID hubId);

    @GetMapping("/hubs/batch")
    List<HubResponseDto> findHubsByIds(@RequestBody List<UUID> ids);
}
