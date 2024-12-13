package com.sparta.company.domain.exception

open class FeignException(val error: Error) : RuntimeException()

class CircuitBreakerOpenException : FeignException(Error.CIRCUIT_BREAKER_OPEN)

class ServerTimeoutException : FeignException(Error.SERVER_TIMEOUT)