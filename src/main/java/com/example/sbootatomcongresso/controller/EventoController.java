package com.example.sbootatomcongresso.controller;

import com.example.sbootatomcongresso.domain.Evento;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("${spring.application.name}/api/eventos")
public class EventoController {

    private List<Evento> eventos;
    private RestTemplate restTemplate;
    Logger logger = LoggerFactory.getLogger(EventoController.class);

    @Autowired
    private EurekaClient eurekaClient;

    public EventoController(RestTemplate restTemplate){
        this.restTemplate = restTemplate;

        this.eventos = new ArrayList<>();

        Evento mesaRedonda = new Evento();
        mesaRedonda.setCodigo("MR01");
        mesaRedonda.setNome("Mesa redonda");
        mesaRedonda.setQuantidadePessoas(10L);
        eventos.add(mesaRedonda);

        Evento palestra = new Evento();
        palestra.setCodigo("PA01");
        palestra.setNome("Palestra");
        palestra.setQuantidadePessoas(3L);
        eventos.add(palestra);

    }

    @GetMapping
    public List<Evento> listar(){
        InstanceInfo instance = eurekaClient.getNextServerFromEureka("ATOMCONGRESSO", false);
        logger.info(instance.getId());
        return eventos;
    }

    @PostMapping
    public ResponseEntity credenciar(){
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("codigo", "C20");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        String url = "http://atomficha/api/fichas";

        ResponseEntity<String> response = this.restTemplate.postForEntity(url, request, String.class);

        return response;
    }

}
