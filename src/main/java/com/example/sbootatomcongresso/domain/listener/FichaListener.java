package com.example.sbootatomcongresso.domain.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class FichaListener {
    private Logger logger = LoggerFactory.getLogger(FichaListener.class);

    @KafkaListener(id = "CONGRESSO", topics = "ficha_topic")
    public void listener(@Payload String payload, @Header(KafkaHeaders.GROUP_ID) String groupId) {
        this.logger.info("Payload: {}", payload);
        this.logger.info("Group id: {}", groupId);
    }
}
