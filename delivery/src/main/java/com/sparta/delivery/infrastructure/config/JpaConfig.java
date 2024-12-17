package com.sparta.delivery.infrastructure.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;
import java.util.UUID;

@EnableJpaAuditing
@Configuration
@RequiredArgsConstructor
public class JpaConfig {

    private final HttpServletRequest request;

    @Bean
    public AuditorAware<UUID> auditorAware() {
        return () -> {
            String userId = request.getHeader("X-Authenticated-User-Id");

            return Optional.ofNullable(userId).map(UUID::fromString);
        };
    }
}
