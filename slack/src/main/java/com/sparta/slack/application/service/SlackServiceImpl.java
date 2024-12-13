package com.sparta.slack.application.service;

import com.sparta.slack.domain.model.Slack;
import com.sparta.slack.domain.service.SlackService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;


@RequiredArgsConstructor
public class SlackServiceImpl implements SlackService {

    private final RestTemplate restTemplate;

    @Value("${slack.webhook.url}")
    private String slackWebhookUrl;

    @Override
    public void sendMessage(String message) {

        //TODO 임시 메시지, 유저 아이디 수정 필요
        //TODO 메시지 DTO를 따로 만들어서 메시지만 발송할 수 있도록..
        Slack slack = Slack.create(UUID.randomUUID(),"임시 메시지 입니다.");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Slack> entity = new HttpEntity<>(slack, headers);

        restTemplate.postForEntity(slackWebhookUrl, entity, String.class);
    }
}
