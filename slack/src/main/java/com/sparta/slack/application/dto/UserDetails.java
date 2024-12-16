package com.sparta.slack.application.dto;

import lombok.Getter;

import java.util.UUID;

public class UserDetails {

    @Getter
    public static class Response{
        private final UUID id;
        private final String username;
        private final String slackId;
        private final String role;
        private final Boolean isApproved;

        public Response(UUID id, String username, String slackId, String role, Boolean isApproved) {
            this.id = id;
            this.username = username;
            this.slackId = slackId;
            this.role = role;
            this.isApproved = isApproved;
        }
    }
}
