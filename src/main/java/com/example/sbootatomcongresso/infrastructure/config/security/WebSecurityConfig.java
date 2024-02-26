package com.example.sbootatomcongresso.infrastructure.config.security;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";
    private final JwtAuthConverter jwtAuthConverter;

    public WebSecurityConfig(JwtAuthConverter jwtAuthConverter) {
        this.jwtAuthConverter = jwtAuthConverter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((authorize) -> authorize
                .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
                .requestMatchers(SWAGGER_PAGES).permitAll()
                .requestMatchers(HttpMethod.GET,"/atom-congresso/api/users/anonymous").permitAll()
                .requestMatchers(HttpMethod.POST,"/actuator/refresh").permitAll()
                .requestMatchers("/atom-congresso/api/eventos").hasRole(USER)
                .requestMatchers("/atom-congresso/api/users").hasRole(ADMIN)
                .anyRequest().authenticated());

        http.oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt
                        .jwtAuthenticationConverter(jwtAuthConverter)
                )
        );

        http.sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        http.csrf((httpSecurityCsrfConfigurer) -> httpSecurityCsrfConfigurer.disable());

        return http.build();
    }

    private static final String[] AUTH_WHITELIST = {
            "/atom-congresso/api/users/anonymous",
            "/atom-congresso/api/eventos/kafka/send"
    };

    private static final String[] SWAGGER_PAGES = {
            "/v2/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/webjars/**"
    };
}
