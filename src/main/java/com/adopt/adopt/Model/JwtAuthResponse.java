package com.adopt.adopt.Model;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
public class JwtAuthResponse {
        private String accessToken;
        private String tokenType;
        private Long expiresIn;
}
