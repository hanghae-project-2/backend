package com.sparta.user.domain;

import lombok.Getter;

@Getter
public enum UserRole {
    MASTER(Authority.MASTER),  // 마스터 권한
    HUB_ADMIN(Authority.HUB_ADMIN),  // 허브 관리자 권한
    DELIVERY_PERSON(Authority.DELIVERY_PERSON), //배달 기사 권한
    COMPANY_ADMIN(Authority.COMPANY_ADMIN);  // 업체 관리자 권한


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
