package com.example.restdemo;

import com.example.restdemo.config.KafkaConfig;
import com.example.restdemo.user.User;
import com.example.restdemo.user.UserListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

@SpringBootApplication
@ComponentScan(basePackageClasses = KafkaConfig.class)
public class RestDemoApplication {
	private static final Logger logger = LoggerFactory.getLogger(RestDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RestDemoApplication.class, args);
	}

	@Bean
	UserListener userListener() {
		return new UserListener();
	}

	@KafkaListener(groupId = "default", topics = "user-mail")
	public void listenAsObject(ConsumerRecord<String, User> cr, @Payload User payload) {
		logger.info(String.format("Message received: %s, %s", cr.key(), cr.toString()));
	}
}
