package com.spring.kafka.controller;


import com.spring.kafka.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka")
public class ApplicationController {

    private final KafkaProducer producer;

    @Autowired
    ApplicationController(KafkaProducer producer){
        this.producer = producer;
    }

    @PostMapping(value = "/publish")
    public void sendMessage(@RequestParam("message") String message){
        this.producer.sendMessage(message);
    }

}
