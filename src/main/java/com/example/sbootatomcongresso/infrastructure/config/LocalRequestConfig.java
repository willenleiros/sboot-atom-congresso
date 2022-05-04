package com.example.sbootatomcongresso.infrastructure.config;

import com.example.sbootatomcongresso.infrastructure.model.URLLocal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocalRequestConfig {

    @Bean
    public URLLocal urlLocal(){
        return new URLLocal();
    }
}
