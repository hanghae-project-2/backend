package com.sparta.slack.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

public class HubInfo {

    @Getter
    public static class Response{

        private final UUID hubId;
        private final String hubName;

        public Response(UUID hubId, String hubName) {
            this.hubId = hubId;
            this.hubName = hubName;
        }

    }
}
