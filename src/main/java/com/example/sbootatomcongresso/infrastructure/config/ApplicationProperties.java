package com.example.sbootatomcongresso.infrastructure.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "application.microservice")
public class ApplicationProperties {

    private Docker docker;

    @Getter
    @Setter
    public static class Docker{
        private Boolean enabled;
    }
}
