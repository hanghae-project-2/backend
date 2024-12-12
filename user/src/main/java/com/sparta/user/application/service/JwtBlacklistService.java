package com.sparta.user.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class JwtBlacklistService {

    private final RedisTemplate<String, Object> redisTemplate;
    private static final String ACCESS_TOKEN_PREFIX = "blacklist:accessToken:";
    private static final String REFRESH_TOKEN_PREFIX = "blacklist:refreshToken:";

    public void addToBlacklist(String token, long expirationInMillis, boolean isAccessToken) {
        String key = getKey(token, isAccessToken);
        redisTemplate.opsForValue().set(key, "blacklisted", Duration.ofMillis(expirationInMillis));
    }

    public boolean isBlacklisted(String token, boolean isAccessToken) {
        String key = getKey(token, isAccessToken);
        return redisTemplate.hasKey(key);
    }

    private String getKey(String token, boolean isAccessToken) {
        return (isAccessToken ? ACCESS_TOKEN_PREFIX : REFRESH_TOKEN_PREFIX) + token;
    }

}
