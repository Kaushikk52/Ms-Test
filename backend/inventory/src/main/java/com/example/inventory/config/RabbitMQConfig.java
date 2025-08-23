package com.example.inventory.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public TopicExchange userExchange() {
        return new TopicExchange("user-exchange");
    }

    @Bean
    public TopicExchange inventoryExchange() {
        return new TopicExchange("inventory-exchange");
    }

    @Bean
    public Queue userCreatedQueue() {
        return new Queue("user-created-queue");
    }

    @Bean
    public Binding binding(Queue userCreatedQueue, TopicExchange userExchange) {
        return BindingBuilder.bind(userCreatedQueue).to(userExchange).with("user.created");
    }

}
