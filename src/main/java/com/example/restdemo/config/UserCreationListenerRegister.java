package com.example.restdemo.config;

import com.example.restdemo.user.UserCreationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserCreationListenerRegister {
    @Bean
    UserCreationListener userListener() {
        return new UserCreationListener();
    }
}
