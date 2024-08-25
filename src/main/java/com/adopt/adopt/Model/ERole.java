package com.adopt.adopt.Model;

import lombok.Getter;

@Getter
public enum ERole {
    CUSTOMER("CUSTOMER"),
    EMPLOYEE("EMPLOYEE"),
    ADMIN("ADMIN");

    private final String role;

    ERole(String role) {
        this.role = role;
    }

}
