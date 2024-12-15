package com.sparta.slack.application.service;

import com.sparta.slack.domain.service.SlackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.MediaType.APPLICATION_JSON;


@RequiredArgsConstructor
@Service
@Slf4j
public class SlackServiceImpl implements SlackService {

    private final RestTemplate restTemplate;

    @Value("${slack.webhook.url}")
    private String slackWebhookUrl;

    @Value("${slack.token}")
    private String slackToken;

    @Override
    public HttpHeaders setHeadersForSlack(MediaType mediaType) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + slackToken);
        headers.setContentType(mediaType);
        return headers;
    }

    @Override
    public void sendMessage(String message) {

        String requestBody = "{\"text\": \"" + message + "\"}";

        HttpHeaders headers = setHeadersForSlack(APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(slackWebhookUrl, entity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            log.info("slack 전송 성공");
        } else {
            log.error("slack 전송 실패");
        }
    }


}
