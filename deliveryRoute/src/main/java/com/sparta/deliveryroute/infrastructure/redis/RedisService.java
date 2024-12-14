package com.sparta.deliveryroute.infrastructure.redis;

import com.sparta.deliveryroute.application.dto.RouteResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RedisService {

	private static final String PREFIX = "hut-route::";
	private static final String ARROW = " -> ";
	private final RedisTemplate<String, Object> redisTemplate;

	public RouteResult getHash(String startHubName, String endHubName) {
		return (RouteResult) redisTemplate.opsForHash().get(startHubName, endHubName);
	}

	public UUID getValue(String startHubName, String endHubName) {
		return UUID.fromString(
				Objects.requireNonNull(redisTemplate.opsForValue().get(PREFIX + startHubName + ARROW + endHubName)).toString()
		);
	}
}
