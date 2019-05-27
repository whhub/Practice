package com.example.SensorDataGenerator;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class RabbitConfig {
    public static final String HELLO_QUEUE = "lyTest";

//    @Bean
//    public Queue helloQueue() {
//        return new Queue(HELLO_QUEUE);
//    }
}
