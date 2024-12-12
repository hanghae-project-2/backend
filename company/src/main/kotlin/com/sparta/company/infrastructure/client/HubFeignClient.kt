package com.sparta.company.infrastructure.client

import com.sparta.company.domain.client.HubClient
import com.sparta.company.presentation.api.response.Response
import io.github.resilience4j.retry.annotation.Retry
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.util.*

@FeignClient(
    name = "hub-server",
    fallbackFactory = HubFeignClientFallbackFactory::class
)
interface HubFeignClient : HubClient {

    @Retry(name = "hub")
    @GetMapping("/hubs/company/{hubId}")
    override fun existHub(
        @PathVariable hubId: UUID
    ): Response<Boolean>

}


