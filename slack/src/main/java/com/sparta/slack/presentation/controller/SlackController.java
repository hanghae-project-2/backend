package com.sparta.slack.presentation.controller;

import com.sparta.slack.domain.service.SlackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/slack")
@RequiredArgsConstructor
public class SlackController {

    private final SlackService slackService;

    @PostMapping()
    public String sendMessage() {
        slackService.sendMessage("HI");
        return "its done";
    }
}
