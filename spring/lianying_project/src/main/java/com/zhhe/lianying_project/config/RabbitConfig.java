package com.zhhe.lianying_project.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.Iterator;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-02-26 15:13
 */
@Configuration
public class RabbitConfig
{
    public static String queueName="lyTest";

    @Bean
    public Queue queue()
    {
        return new Queue(queueName,true,false,true);
    }

    @Bean
    public FanoutExchange fanoutExchange()
    {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    Binding bindingExchange(Queue queue,FanoutExchange fanoutExchange)
    {
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }
}
