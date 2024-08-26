package com.adopt.adopt.Model;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@Getter
public enum ERole {
    CUSTOMER("CUSTOMER"),
    ADMIN("ADMIN");

    private final String role;

    ERole(String role) {
        this.role = role;
    }

    public List<SimpleGrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.role));
    }

}
