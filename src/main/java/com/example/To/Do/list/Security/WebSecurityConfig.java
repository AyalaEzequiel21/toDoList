package com.example.To.Do.list.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity, AuthenticationManager authManager) throws Exception {
        httpSecurity
                .csrf((csrf) ->
                        csrf.disable())
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/signup").permitAll()
                        .requestMatchers("/login").permitAll()
                        .anyRequest().authenticated()
                );
                return httpSecurity.build();

    }
}
