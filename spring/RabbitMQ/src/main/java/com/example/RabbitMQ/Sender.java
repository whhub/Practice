package com.example.RabbitMQ;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Sender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void Send() {
        String context = "hello " + new Date();
        System.out.println("Sender: " + context);

        this.rabbitTemplate.convertAndSend("hello", context);

        for (int i =0; i< 10; i++) {
            String msg = "hello, 序号: " + i;
            System.out.println("Sender, " + msg);
            rabbitTemplate.convertAndSend("queue-test", msg);
        }

    }
}
