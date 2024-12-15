package com.sparta.slack.domain.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

public interface SlackService {

    void sendMessage(String text);

    HttpHeaders setHeadersForSlack(MediaType mediaType);

}
