package com.zhhe.lianying_project.rabbitmq;

import com.zhhe.lianying_project.config.RabbitConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-02-26 15:20
 */
@Component
public class Producer
{
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String msg)
    {
        this.rabbitTemplate.convertAndSend(RabbitConfig.queueName,msg);
    }
}
