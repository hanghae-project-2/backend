package com.sparta.hub.infrastructure.redis

import com.sparta.hub.application.dto.RouteResult
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.util.*

@Service
class RedisService(
    private val redisTemplate: RedisTemplate<String, Any>
) {

    fun setHubRoute(key: String, hubRouteId: UUID) {
        redisTemplate.opsForValue().set(key, hubRouteId)
    }

    fun setHubRouteResult(key1: String, key2: String, value: RouteResult) {
        redisTemplate.opsForHash<String, RouteResult>().put(
            key1,
            key2,
            value
        )
    }

    fun getHubRouteResult(key1: String, key2: String): RouteResult? {
        return redisTemplate.opsForHash<String, RouteResult>().get(key1, key2)
    }

    fun getHubRouteByStartingWithKey(key: String): List<RouteResult> {
        return redisTemplate.opsForHash<String, RouteResult>().values(key)
    }


}