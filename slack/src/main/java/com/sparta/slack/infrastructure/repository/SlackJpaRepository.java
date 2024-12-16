package com.sparta.slack.infrastructure.repository;

import com.sparta.slack.domain.model.Slack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SlackJpaRepository extends JpaRepository<Slack, UUID> {
}
