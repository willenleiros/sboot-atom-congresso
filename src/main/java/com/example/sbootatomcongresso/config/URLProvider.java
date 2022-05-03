package com.example.sbootatomcongresso.config;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class URLProvider {

    private static final String PROTOCOLO = "http://";
    private static final String SERVER_LOCAL = "localhost";
    private static final String PORTA_LOCAL = "8080";
    private static final String URI_ATOM_CREDENCIAR = "/atomficha/api/fichas";
    private static final String SEPARATION_PORT = ":";

    @Autowired
    private EurekaClient eurekaClient;

    public String mountResourceUrlFromLocal(){
        return PROTOCOLO+SERVER_LOCAL+SEPARATION_PORT+PORTA_LOCAL+URI_ATOM_CREDENCIAR;
    }

    public String mountResourceUrlFromServiceDiscovery(){
        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("ATOMFICHA", false);
         return PROTOCOLO+
                instanceInfo.getVIPAddress()+
                SEPARATION_PORT+
                instanceInfo.getMetadata().get("management.port")+
                URI_ATOM_CREDENCIAR;
    }

}
