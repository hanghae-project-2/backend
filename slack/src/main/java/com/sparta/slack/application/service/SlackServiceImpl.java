package com.sparta.slack.application.service;

import com.sparta.slack.application.dto.HubOptimization;
import com.sparta.slack.domain.service.SlackService;
import com.sparta.slack.infrastructure.client.HubClient;
import com.sparta.slack.infrastructure.client.UserClient;
import com.sparta.slack.infrastructure.dto.HubOptimizeApi;
import com.sparta.slack.infrastructure.dto.SlackEvent;
import com.sparta.slack.infrastructure.dto.UserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON;


@RequiredArgsConstructor
@Service
@Slf4j
public class SlackServiceImpl implements SlackService {

    private final RestTemplate restTemplate;
    private final HubClient hubClient;
    private final UserClient userClient;

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
    public void processSlack(SlackEvent slackEvent) {
        getHubOptimizationRoute(slackEvent.originHubId(),slackEvent.destinationHubId());
    }

    @Override
    public String generateMessage(){};

    @Override
    public UserDetails.Response getUserDetailsById(UUID userId){
        return userClient.getUserById(userId);
    }

    @Override
    public HubOptimization.Response getHubOptimizationRoute(UUID startHubId, UUID endHubId){
        return HubOptimization.Response.from(hubClient.findHubRoutesByName(startHubId, endHubId));
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
