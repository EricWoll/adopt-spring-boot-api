package com.adopt.adopt.Security;

import com.adopt.adopt.Model.ERole;
import com.adopt.adopt.Security.Jwt.JwtFilter;
import com.adopt.adopt.Security.Service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(Customizer.withDefaults())
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
                                    "api/v1/adoption-records/**",
                                    "/api/v1/images/**"
                            ).permitAll();
                            authHttp.requestMatchers(
                                    "api/v1/auth/**"
                            ).permitAll();
                            authHttp.requestMatchers(
                                    HttpMethod.POST,
                                    "/api/v1/images/**"
                            ).hasAnyRole(ERole.CUSTOMER.getRole(), ERole.ADMIN.getRole());
                            authHttp.requestMatchers(
                                    "api/v1/users/**",
                                    "api/v1/adoption-records/**"
                            ).hasAnyRole(ERole.CUSTOMER.getRole(), ERole.ADMIN.getRole());
                            authHttp.requestMatchers(
                                    "api/v1/admin/**",
                                    "/api/v1/images/**"
                            ).hasRole(ERole.ADMIN.getRole());
                            authHttp.anyRequest().authenticated();
                        }
                )
                .httpBasic(Customizer.withDefaults())
                .authenticationProvider(authenticationProvider())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:3000");
            }
        };
    }
}
