package com.spring.kafka.producer;


import com.spring.kafka.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaProducer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    private static final String TOPIC = "users";

    /**
     * this code is send message to broken kafka: String
     */


    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;


    public void sendMessage(User user){
        logger.info("send message = {} to topic = {}", user, TOPIC);
        this.kafkaTemplate.send(TOPIC, user);
    }


}
