package com.example.sbootatomcongresso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SbootAtomCongressoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbootAtomCongressoApplication.class, args);
    }

}
