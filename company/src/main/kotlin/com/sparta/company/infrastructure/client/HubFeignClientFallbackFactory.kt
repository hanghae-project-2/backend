package com.sparta.company.infrastructure.client

import com.sparta.company.domain.exception.CircuitBreakerOpenException
import com.sparta.company.domain.exception.ServerTimeoutException
import io.github.resilience4j.circuitbreaker.CallNotPermittedException
import jakarta.ws.rs.InternalServerErrorException
import org.springframework.cloud.openfeign.FallbackFactory
import org.springframework.stereotype.Component
import java.util.*
import java.util.concurrent.TimeoutException

@Component
class HubFeignClientFallbackFactory : FallbackFactory<HubFeignClient> {
    override fun create(cause: Throwable?): HubFeignClient {
        return object : HubFeignClient {
            override fun existHub(hubId: UUID): Boolean {
                when (cause) {
                    is CallNotPermittedException -> {
                        throw CircuitBreakerOpenException()
                    }

                    is TimeoutException -> {
                        throw ServerTimeoutException()
                    }

                    else -> {
                        throw InternalServerErrorException()
                    }
                }
            }
        }
    }
}