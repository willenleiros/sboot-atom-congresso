package com.example.sbootatomcongresso.controller;

import com.example.sbootatomcongresso.config.URLProvider;
import com.example.sbootatomcongresso.domain.Evento;
import com.example.sbootatomcongresso.domain.Ficha;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("${spring.application.name}/api/eventos")
public class EventoController {

    private List<Evento> eventos;
    Logger logger = LoggerFactory.getLogger(EventoController.class);

    private URLProvider urlProvider;

    public EventoController(URLProvider urlProvider){
        this.urlProvider = urlProvider;

        this.eventos = List.of(
                new Evento("MR01","Mesa redonda",10L),
                new Evento("PA01","Palestra",3L));
    }

    @GetMapping
    public List<Evento> listar(){
        return eventos;
    }

    @PostMapping
    public ResponseEntity credenciar(@RequestBody Ficha ficha){
        logger.info("credenciar no microservice: "+urlProvider.mountResourceUrlFromServiceDiscovery());
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Ficha> request = new HttpEntity<>(ficha);
        ResponseEntity response = restTemplate
                .postForEntity(urlProvider.mountResourceUrlFromServiceDiscovery(), request, Boolean.class);
        return response;
    }

}
