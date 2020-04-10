package com.spring.kafka.controller;


import com.spring.kafka.model.User;
import com.spring.kafka.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/kafka")
public class ApplicationController {

    private final KafkaProducer producer;

    @Autowired
    ApplicationController(KafkaProducer producer){
        this.producer = producer;
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public void sendMessage(@RequestBody User message){
        this.producer.sendMessage(message);
    }

}
