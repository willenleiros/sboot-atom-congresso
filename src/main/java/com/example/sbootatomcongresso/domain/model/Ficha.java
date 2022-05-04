package com.example.sbootatomcongresso.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class Ficha {
    private String local;
    private String codigo;
}
