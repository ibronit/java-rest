package com.example.restdemo.user;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class UserCreationKafkaListener {
    private static final Logger logger = LoggerFactory.getLogger(UserCreationKafkaListener.class);

    @KafkaListener(groupId = "default", topics = "user-creation")
    public void listenAsObject(ConsumerRecord<String, User> cr, @Payload User payload) {
        logger.info(String.format("Message received: %s, %s", cr.key(), cr.toString()));
    }
}
