package com.spring.kafka.controller;


import com.spring.kafka.model.User;
import com.spring.kafka.producer.KafkaProducer;
import com.spring.kafka.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    KafkaProducer kafkaProducer;

    @Autowired
    UserService userService;

    @Value("${kafka.create.topic}")
    private String CREATE_TOPIC;

    @Value("${kafka.update.topic}")
    private String UPDATE_TOPIC;

    @Value("${kafka.delete.topic}")
    private String DELETE_TOPIC;


    @PostMapping(produces = "application/json", consumes = "application/json")
    public void createNewUser(@RequestBody User created){
        logger.info("topic = {}", CREATE_TOPIC);
        this.kafkaProducer.sendMessage(created, CREATE_TOPIC);
    }


    @PutMapping(produces = "application/json", consumes = "application/json")
    public void updateUser(@RequestBody User updated){
        this.kafkaProducer.sendMessage(updated, UPDATE_TOPIC);
    }


    @DeleteMapping(value = "/{userId}")
    public void deleteUser(@PathVariable("userId") int userId){
        this.kafkaProducer.sendMessageParam(userId, DELETE_TOPIC);
    }


    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAllUser(){
        List<User> userList = userService.findAllUser();
        return new ResponseEntity<>(userList, HttpStatus.OK);

    }
}
