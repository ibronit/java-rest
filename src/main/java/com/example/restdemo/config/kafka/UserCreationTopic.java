package com.example.restdemo.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class UserCreationTopic {
    @Value("${app.kafka.topics.user-creation.name}")
    private String topicName;

    @Bean
    public NewTopic createTopic() {
        return TopicBuilder
            .name(this.topicName)
            .replicas(1)
            .partitions(1)
            .replicas(1)
            .build();
    }
}
