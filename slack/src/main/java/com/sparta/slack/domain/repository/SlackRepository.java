package com.sparta.slack.domain.repository;

import com.sparta.slack.domain.model.Slack;
import org.springframework.stereotype.Repository;

@Repository
public interface SlackRepository {

    void save(Slack slack);
}
