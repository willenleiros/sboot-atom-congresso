package com.example.sbootatomcongresso.controller;

import com.example.sbootatomcongresso.domain.Evento;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("${spring.application.name}/api/eventos")
public class EventoController {

    private List<Evento> eventos;

    public EventoController(){
        this.eventos = new ArrayList<>();
    }

    @GetMapping
    public List<Evento> listar(){
        Evento evento = new Evento();
        evento.setNome("Mesa redonda");
        evento.setQuantidadePessoas(10L);
        eventos.add(evento);
        return eventos;
    }

    @PostMapping
    public Boolean credenciar(){
        return false;
    }

}
