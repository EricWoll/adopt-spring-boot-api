package com.adopt.adopt.Security;

import com.adopt.adopt.Model.ERole;
import com.adopt.adopt.Security.Service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    // NEEDS JWT in order to log in!

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        authHttp -> {
                            authHttp.requestMatchers(
                                    HttpMethod.GET,
                                    "api/v1/animals",
                                    "api/v1/animals/**",
                                    "api/v1/users",
                                    "api/v1/users/**",
                                    "/pi/v1/adoption-records",
                                    "api/v1/adoption-records/**"
                            ).permitAll();
                            authHttp.requestMatchers(HttpMethod.POST,
                                    "api/v1/users"
                            ).permitAll();
                            authHttp.requestMatchers(
                                    "api/v1/users/**",
                                    "api/v1/adoption-records/**"
                            ).hasAnyRole(ERole.CUSTOMER.getRole(), ERole.ADMIN.getRole());
                            authHttp.requestMatchers(
                                    "api/v1/admin/**"
                            ).hasRole(ERole.ADMIN.getRole());
                            authHttp.anyRequest().authenticated();
                        }
                )
                .httpBasic(Customizer.withDefaults())
                .authenticationProvider(authenticationProvider())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
}
