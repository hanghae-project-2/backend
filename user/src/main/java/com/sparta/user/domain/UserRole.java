package com.sparta.user.domain;

import lombok.Getter;

@Getter
public enum UserRole {
    MASTER(Authority.MASTER),
    HUB_ADMIN(Authority.HUB_ADMIN),
    DELIVERY_PERSON(Authority.DELIVERY_PERSON),
    COMPANY_ADMIN(Authority.COMPANY_ADMIN);

    private final String authority;

    UserRole(String authority) {
        this.authority = authority;
    }

    public static class Authority {
        public static final String MASTER = "ROLE_MASTER";
        public static final String HUB_ADMIN = "ROLE_HUB_ADMIN";
        public static final String DELIVERY_PERSON = "ROLE_DELIVERY_PERSON";
        public static final String COMPANY_ADMIN = "ROLE_COMPANY_ADMIN";
    }
}
