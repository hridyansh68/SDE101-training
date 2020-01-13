package com.example.java.using_kafka_with_springboot.engine;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Consumer {
    private final Logger logger = LoggerFactory.getLogger(Producer.class);

    @KafkaListener(topics = "first_topic", groupId = "eighth_application")
    public void consume(String message) throws IOException {
        logger.info(String.format("#### -> Consumed message -> %s", message));
    }
}
