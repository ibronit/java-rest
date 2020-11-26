package com.example.restdemo.user;

import com.example.restdemo.mail.MailService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Component
public class UserCreationKafkaListener {
    private static final Logger logger = LoggerFactory.getLogger(UserCreationKafkaListener.class);

    @Autowired
    private MailService mailService;

    @Autowired
    private final SpringTemplateEngine templateEngine;

    public UserCreationKafkaListener(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @KafkaListener(groupId = "default", topics = "user-creation")
    public void listenAsObject(ConsumerRecord<String, User> cr, @Payload User payload) {
        Context context = new Context();
        context.setVariable("fullName", String.format("%s %s", payload.getFirstName(), payload.getLastName()));

        logger.info(String.format("Sending mail to %s", payload.getEmail()));

        mailService.sendEmail(
            payload.getEmail(),
            "New registration",
            templateEngine.process("user-registration.txt", context),
            templateEngine.process("user-registration.html", context)
        );

        logger.info(String.format("Mail sent to %s", payload.getEmail()));
    }
}
