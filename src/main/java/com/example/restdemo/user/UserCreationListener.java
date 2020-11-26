package com.example.restdemo.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.kafka.core.KafkaTemplate;

@RepositoryEventHandler()
public class UserCreationListener {
    @Value("${app.kafka.topics.user-creation.name}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, Object> template;

    private static final Logger logger = LoggerFactory.getLogger(UserCreationListener.class);

    @HandleAfterCreate
    public void handleUserSave(User user) {
        logger.info("Handle after create listener triggered...");
        template.send(topicName, user);
    }
}
