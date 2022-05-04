package com.example.sbootatomcongresso.api.controller;

import com.example.sbootatomcongresso.domain.model.Evento;
import com.example.sbootatomcongresso.domain.model.Ficha;
import com.example.sbootatomcongresso.infrastructure.model.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
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
    private URL url;

    public EventoController(@Qualifier("eureka") URL url){
        this.url = url;
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
        logger.info("credenciar no microservice: "+ url.montar("ATOMFICHA","/atomficha/api/fichas"));
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Ficha> request = new HttpEntity<>(ficha);
        ResponseEntity response = restTemplate
                .postForEntity(url.montar("ATOMFICHA","/atomficha/api/fichas"), request, Boolean.class);
        return response;
    }

}
