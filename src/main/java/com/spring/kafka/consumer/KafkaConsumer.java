package com.spring.kafka.consumer;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.exceptions.ClosedOnExpiredPasswordException;
import com.spring.kafka.model.User;
import com.spring.kafka.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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


    @KafkaListener(topics = "${kafka.create.topic}", groupId = "${kafka.groupId}", containerFactory = "kafkaListener")
    public ResponseEntity<?> createNewUser(User message){
        logger.info("consume message and create User = {}", message);

        try{
            User saved = userService.createNewUser(message);
            logger.info("create user = {} successfully", message);
            return new ResponseEntity<>(saved, HttpStatus.OK);
        } catch (NullPointerException ex){
            logger.error("create error!!!");
            logger.error("Null pointer exception: {}", ex);
            return new ResponseEntity<>(HttpStatus.SEE_OTHER.getReasonPhrase(), HttpStatus.SEE_OTHER);
        }

    }


    @KafkaListener(topics = "${kafka.update.topic}", groupId = "${kafka.groupId}", containerFactory = "kafkaListener")
    public ResponseEntity<?> updateUser(User message){
        logger.info("consume message and update User = {}",message);
        User updated = userService.updateUser(message);

        if(updated != null){
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase(), HttpStatus.NOT_ACCEPTABLE);
        }
    }


    @KafkaListener(topics = "${kafka.delete.topic}", groupId = "${kafka.groupId}", containerFactory = "integerKafkaListener")
    public ResponseEntity<?> deleteUserById(int message){

        User deleted = userService.deleteUserById(message);

        if (deleted != null){
            return new ResponseEntity<>(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
        }
    }

}
