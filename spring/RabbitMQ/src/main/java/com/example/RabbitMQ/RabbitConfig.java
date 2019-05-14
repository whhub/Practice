package com.example.RabbitMQ;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitConfig {
    public static final String HELLO_QUEUE = "hello";

    @Bean
    public Queue helloQueue() {
        return new Queue(HELLO_QUEUE);
    }
}
