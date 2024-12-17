package com.sparta.slack.application.dto;

import lombok.Getter;

public class CompanyDetails {

    @Getter
    public static class Response {
        private final String name;
        private final String address;
        private final String type;
        private final String hubId;

        public Response(String name, String address, String type, String hubId) {
            this.name = name;
            this.address = address;
            this.type = type;
            this.hubId = hubId;
        }
    }

}
