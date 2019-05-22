package com.example.RabbitMQ;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.rabbitmq.client.*;

import java.io.IOException;

@Component
@RabbitListener(queues = "hello")
public class Consumer {

    @RabbitHandler
    public void process(String hello, Channel channel, Message message) throws IOException {
        try {
            String msg = new String(message.getBody());
            System.out.println("receive: " + msg);

            int num = msg.toCharArray()[msg.length()-1] -'0';
            if(num %2 ==0)
            {
                throw new IOException();
            }

            // 采用手动应答模式，手动确认应答更为安全稳定
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            System.out.println("IOException");
        }

    }
}
