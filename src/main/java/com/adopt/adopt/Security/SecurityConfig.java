package com.adopt.adopt.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    // NEEDS JWT in order to log in!
    // Also... add " http.formLogin(form -> form.loginProcessingUrl(".....")) "

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(
                        authHttp -> {
                            authHttp.requestMatchers(
                                    HttpMethod.GET,
                                    "/api/v1/animals",
                                    "/api/v1/animals/*",
                                    "/api/v1/users",
                                    "/api/v1/users/*",
                                    "/api/v1/adoption-records",
                                    "/api/v1/adoption-records/*"
                            ).permitAll();
                            authHttp.anyRequest().authenticated();
                        }
                )
                .build();
    }
}
