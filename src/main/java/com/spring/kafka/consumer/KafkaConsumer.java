package com.spring.kafka.consumer;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.kafka.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    private final ObjectMapper mapper = new ObjectMapper();


    @KafkaListener(topics = "users", groupId = "group_id", containerFactory = "kafkaListener")
    public void consume(User message) throws Exception{
        logger.info("Consume message = {}", message);
    }

}
