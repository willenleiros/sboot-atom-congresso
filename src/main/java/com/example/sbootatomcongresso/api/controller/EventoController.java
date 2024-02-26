package com.example.sbootatomcongresso.api.controller;

import com.example.sbootatomcongresso.api.feign.FichaClient;
import com.example.sbootatomcongresso.domain.model.Evento;
import com.example.sbootatomcongresso.domain.model.Ficha;
import com.example.sbootatomcongresso.infrastructure.exception.DataNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    private List<Evento> eventos;
    private Logger logger = LoggerFactory.getLogger(EventoController.class);
    private final FichaClient fichaClient;
    private final KafkaTemplate kafkaTemplate;

    public EventoController(FichaClient fichaClient, KafkaTemplate kafkaTemplate){
        this.fichaClient = fichaClient;
        this.kafkaTemplate = kafkaTemplate;
        this.eventos = List.of(
                new Evento("MR01","Mesa redonda",10L),
                new Evento("PA01","Palestra",3L));
    }

    @GetMapping
    public List<Evento> listar(){
        return eventos;
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Evento> findByCodigo(@PathVariable String codigo){
        return ResponseEntity.ok(eventos
                .stream()
                .filter(evento -> codigo.equalsIgnoreCase(evento.getCodigo()))
                .findAny().orElseThrow(() -> new DataNotFoundException("Objeto não encontrado")));
    }

    @PostMapping
    public ResponseEntity<List<Ficha>> credenciar(@RequestBody Ficha ficha){
        logger.info("credenciar no microservice: ");
        return this.fichaClient.credenciar(ficha);
    }

    @PostMapping("/kafka/send")
    public ResponseEntity<String> kafkaSend(@RequestBody Ficha ficha){
        logger.info("produce msg: {}",ficha);
        this.kafkaTemplate.send("ficha_topic",
                String.format("%s -> %s",LocalDateTime.now(),ficha.toString()));
        return ResponseEntity.ok("");
    }

}
