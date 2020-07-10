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

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, Integer> kafkaTemplateInteger;


    public void sendUser(User user, String topic){
        logger.info("send user: {} to topic: {}", user, topic);
        this.kafkaTemplate.send(topic, user);
    }


    public void sendIdOfUser(int userId, String topic){
        logger.info("send id: {} to topic = {}", userId, topic);
        this.kafkaTemplateInteger.send(topic, userId);
    }

}
