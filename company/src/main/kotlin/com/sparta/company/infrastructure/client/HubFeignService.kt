package com.sparta.company.infrastructure.client

import com.sparta.company.application.client.HubService
import io.github.resilience4j.retry.annotation.Retry
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

@FeignClient(
    name = "hub-server",
    fallbackFactory = HubFeignServiceFallbackFactory::class
)
interface HubFeignService : HubService {

    @Retry(name = "hub")
    @GetMapping("/hubs/company/{hubId}")
    override fun existHub(
        @PathVariable hubId: UUID,
    ): Boolean

    @Retry(name = "hub")
    @GetMapping("/hubs/company")
    override fun existHub(
        @RequestParam hubId: UUID,
        @RequestParam userId: String,
    ): Boolean

}


