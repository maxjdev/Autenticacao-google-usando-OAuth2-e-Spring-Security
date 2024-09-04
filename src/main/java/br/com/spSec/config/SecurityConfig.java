package br.com.spSec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(
                        auth -> {
                            auth.requestMatchers("/public").permitAll();
                            auth.requestMatchers("/logout").permitAll();
                            auth.anyRequest().authenticated();
                        }
                )
                .oauth2Login(Customizer.withDefaults())
                .oauth2ResourceServer(config -> {
                    config.jwt(Customizer.withDefaults());
                })
                .build();
    }
}