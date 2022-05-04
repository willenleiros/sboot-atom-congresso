package com.example.sbootatomcongresso.infrastructure.config;

import com.example.sbootatomcongresso.infrastructure.model.URLEureka;
import com.netflix.discovery.EurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EurekaClientRequestConfig {

    @Bean
    public URLEureka urlEureka(EurekaClient eurekaClient){
        return new URLEureka(eurekaClient);
    }

}
