package com.spring.kafka.producer;


import org.apache.kafka.common.protocol.types.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    private static final String TOPIC = "users";


    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    public void sendMessage(String message){
        logger.info(String.format("#### -> producing message -> %s", message));
        this.kafkaTemplate.send(TOPIC, message);
    }

}
