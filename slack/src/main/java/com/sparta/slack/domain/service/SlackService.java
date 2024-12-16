package com.sparta.slack.domain.service;

import com.sparta.slack.application.dto.HubOptimization;
import com.sparta.slack.infrastructure.dto.SlackEvent;
import com.sparta.slack.infrastructure.dto.UserDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface SlackService {

    void sendMessage(String text);

    HttpHeaders setHeadersForSlack(MediaType mediaType);

    void processSlack(SlackEvent slackEvent);

    HubOptimization.Response getHubOptimizationRoute(UUID startHubId, UUID endHubId);

    UserDetails.Response getUserDetailsById(UUID userId);

}
