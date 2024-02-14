package com.example.sbootatomcongresso.domain.service.abstracts;

public interface HttpService<T,Y> {
    Y post(T dto);

    Class<Y> getEntityClassResponse();

    Class<T> getEntityClassRequest();

    String getResource();
}
