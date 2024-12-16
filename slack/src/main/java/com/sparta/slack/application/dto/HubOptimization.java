package com.sparta.slack.application.dto;

import com.sparta.slack.infrastructure.dto.HubOptimizeApi;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public class HubOptimization {

    public record Request(@NotNull UUID startHubId, @NotNull String endHubId){

    }

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

        public static Response from(HubOptimizeApi.Response hubOptimizeApi){
            return new Response(hubOptimizeApi.distance(), hubOptimizeApi.time(), hubOptimizeApi.path());
        }

    }

}