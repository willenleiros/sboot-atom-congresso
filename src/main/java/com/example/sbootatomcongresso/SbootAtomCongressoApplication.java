package com.example.sbootatomcongresso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SbootAtomCongressoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbootAtomCongressoApplication.class, args);
    }

}
