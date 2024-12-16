package com.sparta.slack.infrastructure.repository;

import com.sparta.slack.domain.model.Slack;
import com.sparta.slack.domain.repository.SlackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SlackRepositoryImpl implements SlackRepository {

    private final SlackJpaRepository slackJpaRepository;

    //TODO : order에서 createdby 받아와서 저장하기
    @Override
    public void save(Slack slack) {
        slackJpaRepository.save(slack);
    }
}
