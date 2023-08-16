package com.konzerra.bureaucracy_enginev2_java.domain.user.role;

import lombok.Getter;

@Getter
public enum RoleNames {

    ADMIN("ADMIN"),
    USER("USER");
    private final String name;

    RoleNames(String name) {
        this.name = name;
    }
}
