package com.sparta.slack.infrastructure.dto;

import java.util.List;

public class HubOptimizeApi {

    public record Response(int distance, int time, List<String> path) {
    }

}
