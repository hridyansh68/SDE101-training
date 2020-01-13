package com.example.java.using_kafka_with_springboot.controllers;


import com.example.java.using_kafka_with_springboot.engine.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaContorller {
    private Producer producer;

    @Autowired
    public void KafkaController(Producer producer) {
        this.producer = producer;
    }



    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message){
        this.producer.sendMessage(message);
    }
}
