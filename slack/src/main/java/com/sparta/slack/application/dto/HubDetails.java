package com.sparta.slack.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

public class HubDetails {

    @Getter
    public static class Response{

        @NotNull
        private final int distance;

        @NotNull
        private final int time;

        @NotNull
        private final List<String> path;

        private Response(int distance, int time, List<String> path) {
            this.distance = distance;
            this.time = time;
            this.path = path;

        }

    }

}