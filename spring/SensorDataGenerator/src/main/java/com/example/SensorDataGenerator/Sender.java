package com.example.SensorDataGenerator;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Sender {

    @Autowired
    //private AmqpTemplate rabbitTemplate;
    private RabbitTemplate rabbitTemplate;

    public void Send(String message) {
        this.rabbitTemplate.convertAndSend(RabbitConfig.HELLO_QUEUE, message);
    }
}
