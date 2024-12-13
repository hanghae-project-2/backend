package com.sparta.company.infrastructure.client

import com.sparta.company.application.exception.CircuitBreakerOpenException
import com.sparta.company.application.exception.InternalServerErrorException
import com.sparta.company.application.exception.ServerTimeoutException
import com.sparta.company.presentation.api.response.Response
import io.github.resilience4j.circuitbreaker.CallNotPermittedException
import org.springframework.cloud.openfeign.FallbackFactory
import org.springframework.stereotype.Component
import java.util.*
import java.util.concurrent.TimeoutException

@Component
class HubFeignServiceFallbackFactory : FallbackFactory<HubFeignService> {
    override fun create(cause: Throwable?): HubFeignService {
        return object : HubFeignService {
            override fun existHub(hubId: UUID): Response<Boolean> {
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