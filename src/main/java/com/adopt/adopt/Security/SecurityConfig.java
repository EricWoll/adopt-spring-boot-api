package com.adopt.adopt.Security;

import com.adopt.adopt.Model.ERole;
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
                            authHttp.requestMatchers(
                                    "/api/v1/users",
                                    "/api/v1/users/*",
                                    "/api/v1/adoption-records",
                                    "/api/v1/adoption-records/*"
                            ).hasAnyRole(ERole.CUSTOMER.getRole(), ERole.ADMIN.getRole());
                            authHttp.requestMatchers(
                                    "/api/v1/admin",
                                    "/api/v1/admin/*"
                            ).hasRole(ERole.ADMIN.getRole());
                            authHttp.anyRequest().authenticated();
                        }
                )
                .build();
    }
}
