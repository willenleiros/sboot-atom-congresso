package com.example.sbootatomcongresso.infrastructure.model;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier("eureka")
public class URLEureka implements URL {

    private static final String PROTOCOLO = "http://";
    private static final String SEPARATION_PORT = ":";
    private EurekaClient eurekaClient;

    public URLEureka(EurekaClient eurekaClient){
        this.eurekaClient = eurekaClient;
    }

    @Override
    public String montar(String applicationName, String uri) {
         InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka(applicationName, false);
         return PROTOCOLO+
                instanceInfo.getVIPAddress()+
                SEPARATION_PORT+
                instanceInfo.getMetadata().get("management.port")+
                uri;
    }
}
