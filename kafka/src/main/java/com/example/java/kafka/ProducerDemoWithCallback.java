package com.example.java.kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerDemoWithCallback {

    public static void main(String[] args) {

        final Logger LOGGER = LoggerFactory.getLogger(ProducerDemoWithCallback.class);

        String bootStrapServers = "127.0.0.1:9092";

        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootStrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

        KafkaProducer<String,String> producer = new KafkaProducer<String, String>(properties);

        for(int i=0;i<10;i++) {
            ProducerRecord<String, String> record = new ProducerRecord<String, String>("first_topic", "hope it works"+ Integer.toString(i));

            producer.send(record, new Callback() {
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e == null) {
                        LOGGER.info("recieved new metadata: \n" +
                                "Topic data: " + recordMetadata.topic());
                    } else {
                        LOGGER.error("Error while producing", e);
                    }
                }
            });
        }

        producer.flush();

        producer.close();

    }
}
