package com.sparta.slack.application.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public class HubOptimization {

    public record Request(@NotNull UUID startHubId, @NotNull String endHubId){

    }

    public record Response(@NotNull int distance,@NotNull int time, @NotNull List<String> path){

    }

}