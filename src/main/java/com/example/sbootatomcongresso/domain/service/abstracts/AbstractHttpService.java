package com.example.sbootatomcongresso.domain.service.abstracts;

import com.example.sbootatomcongresso.infrastructure.config.security.JwtAuthConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.client.RestTemplate;

public abstract class AbstractHttpService<T,Y> implements HttpService<T,Y> {
    protected RestTemplate restTemplate;
    protected Logger log = LoggerFactory.getLogger(AbstractHttpService.class);
    protected final JwtAuthConverter jwtAuthConverter;

    public AbstractHttpService(RestTemplate restTemplate, JwtAuthConverter jwtAuthConverter) {
        this.restTemplate = restTemplate;
        this.jwtAuthConverter = jwtAuthConverter;
    }

    protected HttpEntity httpEntity() {
        return new HttpEntity<>(getDefaultHeaders());
    }

    protected HttpEntity httpEntity(T dto) {
        return new HttpEntity<>(dto,getDefaultHeaders());
    }

    private HttpHeaders getDefaultHeaders(){
        JwtAuthenticationToken jwtAuthenticationToken = this.jwtAuthConverter.getJwtAuthenticationToken();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            headers.add("Authorization",
                    String.format("Bearer %s",jwtAuthenticationToken.getToken().getTokenValue()));
            return headers;
        }
        catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public abstract Class<Y> getEntityClassResponse();

    public abstract Class<T> getEntityClassRequest();
}
