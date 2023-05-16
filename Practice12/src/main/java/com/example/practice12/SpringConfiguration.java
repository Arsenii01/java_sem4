package com.example.practice12;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class SpringConfiguration {
    @Bean
    public InitDestroyMethods initDestroyMethods() {
        return new InitDestroyMethods();
    }
}
