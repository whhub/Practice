package com.example.RabbitMQ;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.rabbitmq.client.*;

import java.io.IOException;

@Component
public class Consumer {

    @RabbitListener(queues = "hello")
    public void process(Message message, Channel channel) throws IOException {
        // 采用手动应答模式，手动确认应答更为安全稳定
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);

        System.out.println("receive: " + new String(message.getBody()));
    }
}
