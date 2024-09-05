package com.adopt.adopt.Model;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
public class AuthResponse {
        private String accessToken;
        private String refreshToken;
        private String username;
        private String email;
        private String userId;
        private ERole role;
}
