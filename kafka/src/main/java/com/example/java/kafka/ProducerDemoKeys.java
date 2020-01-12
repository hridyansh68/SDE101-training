package com.example.java.kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class ProducerDemoKeys {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        final Logger LOGGER = LoggerFactory.getLogger(ProducerDemoKeys.class);

        String bootStrapServers = "127.0.0.1:9092";

        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootStrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

        KafkaProducer<String,String> producer = new KafkaProducer<String, String>(properties);

        for(int i=10;i<20;i++) {

            String topic = "first_topic";
            String value = "hello world" + Integer.toString(i);
            String key = "id_" + Integer.toString(i);
            ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic,key,value);



            producer.send(record, new Callback() {
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e == null) {
                        LOGGER.info("recieved new metadata: \n" +
                                "Topic data: " + recordMetadata.topic() + "\n" +
                                "Partitions: " + recordMetadata.partition() + "\n" +
                                "Offsets:" + recordMetadata.offset() + "\n"
                        );
                    } else {
                        LOGGER.error("Error while producing", e);
                    }
                }
            }).get();
        }

        producer.flush();

        producer.close();

    }
}
