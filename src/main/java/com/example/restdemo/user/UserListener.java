package com.example.restdemo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.kafka.core.KafkaTemplate;

@RepositoryEventHandler()
public class UserListener {

    @Autowired
    private KafkaTemplate<String, Object> template;

    @HandleAfterCreate
    public void handlePersonSave(User user) {
        template.send("user-mail", user);
    }
}
