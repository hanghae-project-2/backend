package com.sparta.product.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;
import java.util.UUID;

@Configuration
public class AuditorConfig {

    @Bean
    public AuditorAware<UUID> auditorProvider() {
        return () -> {
            UUID currentUserId = getCurrentUserId();
            return Optional.of(currentUserId);
        };
    }

    //TODO 유저 ID 받으면 수정
    private UUID getCurrentUserId() {
        return UUID.randomUUID();
    }
}

