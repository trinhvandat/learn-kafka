package com.spring.kafka.consumer;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.exceptions.ClosedOnExpiredPasswordException;
import com.spring.kafka.model.User;
import com.spring.kafka.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    UserService userService;


    @KafkaListener(topics = "users", groupId = "group_id", containerFactory = "kafkaListener")
    public ResponseEntity<?> create(User message){

        logger.info("consume message and create User = {}", message);

        try{
            User saved = userService.create(message);
            logger.info("create user = {} successfully", message);
            return new ResponseEntity<>(saved, HttpStatus.OK);
        } catch (NullPointerException ex){
            logger.error("Null pointer exception: {}", ex);
            return new ResponseEntity<>(HttpStatus.SEE_OTHER.getReasonPhrase(), HttpStatus.SEE_OTHER);
        }

    }

}
