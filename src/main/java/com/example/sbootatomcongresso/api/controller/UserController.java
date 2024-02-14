package com.example.sbootatomcongresso.api.controller;

import com.example.sbootatomcongresso.infrastructure.config.ApplicationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RefreshScope
@RestController
@RequestMapping("${spring.application.name}/api/users")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    private final ApplicationProperties applicationProperties;

    public UserController(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @GetMapping("/anonymous")
    public ResponseEntity<String> getAnonymous() {
        return ResponseEntity. ok (String.format("docker enabled: %s",applicationProperties.getDocker().getEnabled()));
    }

    @PostMapping
    public ResponseEntity autenticar(){
        logger.info("autenticando.....");
        return ResponseEntity.ok("ok");
    }
}
