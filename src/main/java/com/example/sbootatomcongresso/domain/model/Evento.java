package com.example.sbootatomcongresso.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class Evento {
    private String codigo;
    private String nome;
    private Long quantidadePessoas;
}
