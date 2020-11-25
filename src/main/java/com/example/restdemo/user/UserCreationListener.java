package com.example.restdemo.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.kafka.core.KafkaTemplate;

@RepositoryEventHandler()
public class UserCreationListener {

    @Autowired
    private KafkaTemplate<String, Object> template;

    private static final Logger logger = LoggerFactory.getLogger(UserCreationListener.class);

    @HandleAfterCreate
    public void handleUserSave(User user) {
        logger.info("Handle after create listener triggered...");
        template.send("user-registration", user); //TODO: refact hardcoded value
    }
}
