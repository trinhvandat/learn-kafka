package com.spring.kafka.controller;


import com.spring.kafka.model.User;
import com.spring.kafka.producer.KafkaProducer;
import com.spring.kafka.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    KafkaProducer kafkaProducer;

    @Autowired
    UserService userService;

    @Value("${kafka.create.topic}")
    private static String CREATE_TOPIC;

    @Value("${kafka.update.topic}")
    private static String UPDATE_TOPIC;

    @Value("${kafka.delete.topic}")
    private static String DELETE_TOPIC;


    @PostMapping(produces = "application/json", consumes = "application/json")
    public void create(@RequestBody User created){

        this.kafkaProducer.sendMessage(created, CREATE_TOPIC);

    }


    @PostMapping(produces = "application/json", consumes = "application/json")
    public void update(@RequestBody User updated){

        this.kafkaProducer.sendMessage(updated, UPDATE_TOPIC);

    }


    @DeleteMapping(value = "/{userId}",produces = "application/json", consumes = "application/json")
    public String delete(@RequestParam("userId") int userId){

        User deleted = userService.findById(userId);

        if (deleted != null){
            this.kafkaProducer.sendMessage(deleted, DELETE_TOPIC);
            return "send message successful";
        } else {
            logger.error("User not found with id = {}", userId);
            return "User not found";
        }

    }




}
