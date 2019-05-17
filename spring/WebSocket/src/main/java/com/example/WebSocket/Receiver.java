package com.example.WebSocket;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = "hello")
public class Receiver {

    @Autowired
    private MyWebSocket webSocket;

    @RabbitHandler
    public void process(String hello) throws IOException {

        System.out.println("Receiver: " + hello);
        if (webSocket == null) {
            System.out.println("web socket is null");
        }else {
            webSocket.onMessage(hello, null);
        }

    }
}