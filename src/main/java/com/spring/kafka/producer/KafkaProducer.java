package com.spring.kafka.producer;


import com.spring.kafka.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);


    /**
     * this code is send message to broken kafka: String
     */


    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;


    public void sendMessage(User user, String topic){
        logger.info("send message = {} to topic = {}", user, topic);
        this.kafkaTemplate.send(topic, user);
    }

}
