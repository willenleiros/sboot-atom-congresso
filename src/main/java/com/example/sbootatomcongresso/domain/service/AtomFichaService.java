package com.example.sbootatomcongresso.domain.service;

import com.example.sbootatomcongresso.domain.service.abstracts.AbstractHttpService;
import com.example.sbootatomcongresso.domain.model.Ficha;
import com.example.sbootatomcongresso.infrastructure.config.ApplicationProperties;
import com.example.sbootatomcongresso.infrastructure.config.security.JwtAuthConverter;
import com.fasterxml.jackson.databind.JsonNode;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AtomFichaService extends AbstractHttpService<Ficha, JsonNode> {

    private final EurekaClient eurekaClient;
    private final ApplicationProperties applicationProperties;
    protected Logger logger = LoggerFactory.getLogger(AtomFichaService.class);


    public AtomFichaService(RestTemplate restTemplate, JwtAuthConverter jwtAuthConverter,
                            EurekaClient eurekaClient, ApplicationProperties applicationProperties) {
        super(restTemplate,jwtAuthConverter);
        this.eurekaClient = eurekaClient;
        this.applicationProperties = applicationProperties;
    }

    @Override
    public JsonNode post(Ficha dto) {
        return this.restTemplate.exchange(
                getResource(), HttpMethod.POST, httpEntity(dto),
                getEntityClassResponse()).getBody();
    }

    @Override
    public Class<JsonNode> getEntityClassResponse() {
        return JsonNode.class;
    }

    @Override
    public Class<Ficha> getEntityClassRequest() {
        return Ficha.class;
    }

    @Override
    public String getResource() {

        final String PROTOCOLO = "http://";
        final String SEPARATION_PORT = ":";
        final String APPLICATION_NAME = "ATOM-FICHA";
        final String URI = "/api/fichas";

        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka(APPLICATION_NAME, false);
        logger.info(instanceInfo.toString());
        if(applicationProperties.getDocker().getEnabled())
            return PROTOCOLO+
                    instanceInfo.getInstanceId()+
                    SEPARATION_PORT+
                    instanceInfo.getMetadata().get("port")+
                    URI;
        else
            return PROTOCOLO+
                    instanceInfo.getIPAddr()+
                    SEPARATION_PORT+
                    instanceInfo.getMetadata().get("port")+
                    URI;
    }
}
