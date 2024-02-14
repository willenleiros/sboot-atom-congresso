package com.example.sbootatomcongresso.api.controller;

import com.example.sbootatomcongresso.domain.service.AtomFichaService;
import com.example.sbootatomcongresso.domain.model.Evento;
import com.example.sbootatomcongresso.domain.model.Ficha;
import com.example.sbootatomcongresso.infrastructure.exception.DataNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${spring.application.name}/api/eventos")
public class EventoController {

    private List<Evento> eventos;
    Logger logger = LoggerFactory.getLogger(EventoController.class);
    private final AtomFichaService atomFichaService;

    public EventoController(AtomFichaService atomFichaService){
        this.atomFichaService = atomFichaService;
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
    public ResponseEntity credenciar(@RequestBody Ficha ficha){
        logger.info("credenciar no microservice: ");
        return ResponseEntity.ok(this.atomFichaService.post(ficha));
    }

}
