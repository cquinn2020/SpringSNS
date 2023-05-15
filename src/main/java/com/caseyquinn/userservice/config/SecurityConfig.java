package com.caseyquinn.userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/users/register").permitAll() // All I understand is that this is a security
                                                                        // filter chain that allows all requests to the
                                                                        // /users/register endpoint
                        .anyRequest().authenticated());
        return http.build();
    }
}
