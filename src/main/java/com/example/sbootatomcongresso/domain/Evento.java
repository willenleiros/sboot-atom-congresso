package com.example.sbootatomcongresso.domain;

import lombok.Data;

@Data
public class Evento {
    private String codigo;
    private String nome;
    private Long quantidadePessoas;
}
