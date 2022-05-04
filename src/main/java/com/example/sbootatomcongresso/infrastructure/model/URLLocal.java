package com.example.sbootatomcongresso.infrastructure.model;

import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier("local")
public class URLLocal implements URL {

    private static final String PROTOCOLO = "http://";
    private static final String SERVER_LOCAL = "localhost";
    private static final String PORTA_LOCAL = "8080";
    private static final String SEPARATION_PORT = ":";

    @Override
    public String montar(String applicationName, String uri) {
        return PROTOCOLO+SERVER_LOCAL+SEPARATION_PORT+PORTA_LOCAL+uri;
    }

}
