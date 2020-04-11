package com.spring.kafka.controller;


import com.spring.kafka.model.User;
import com.spring.kafka.producer.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    KafkaProducer kafkaProducer;


    @PostMapping(produces = "application/json", consumes = "application/json")
    public void create(@RequestBody User created){

        this.kafkaProducer.sendMessage(created);

        logger.info("send request create User = {}", created);

    }

}
