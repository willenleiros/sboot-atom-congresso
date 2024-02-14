package com.example.sbootatomcongresso.infrastructure.exception;

public class DataNotFoundException extends RuntimeException{
    public DataNotFoundException(String id) {
        super(String.format("O dado com ID %s não foi encontrado!", id));
    }
}
