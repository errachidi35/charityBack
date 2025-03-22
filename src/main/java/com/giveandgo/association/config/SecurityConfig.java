package com.giveandgo.association.config;

import com.giveandgo.association.security.CustomUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder; // Injecte le bean défini dans PasswordEncoderConfig

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
                // Endpoints publics
                .requestMatchers("/api/auth/login", "/api/auth/register").permitAll()
                .requestMatchers("/api/missions", "/api/missions/**").permitAll()
                // Endpoints protégés
                .requestMatchers("/api/participations/**").hasAnyRole("BENEVOLE", "MEMBRE", "ADMIN")
                .requestMatchers("/api/discussions/**").hasAnyRole("BENEVOLE", "MEMBRE", "ADMIN")
                .requestMatchers("/api/messages/**").hasAnyRole("BENEVOLE", "MEMBRE", "ADMIN")
                .requestMatchers("/api/missions/**").hasAnyRole("MEMBRE", "ADMIN")
                .requestMatchers("/api/utilisateurs/**").hasRole("ADMIN")
                .requestMatchers("/api/admins/**").hasRole("ADMIN")
                .requestMatchers("/api/membres/**").hasRole("ADMIN")
                .requestMatchers("/api/benevoles/**").hasRole("ADMIN")
                .requestMatchers("/api/dons", "/api/dons/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .httpBasic(httpBasic -> {});
        return http.build();
    }
}