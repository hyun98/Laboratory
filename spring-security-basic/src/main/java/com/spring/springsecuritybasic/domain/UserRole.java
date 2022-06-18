package com.spring.springsecuritybasic.domain;

import lombok.Getter;

@Getter
public enum UserRole {
    ROLE_ADMIN("관리자"), ROLE_MANAGER("매니저"), ROLE_MEMBER("일반사용자");

    private String description;

    UserRole(String description) {
        this.description = description;
    }
}
