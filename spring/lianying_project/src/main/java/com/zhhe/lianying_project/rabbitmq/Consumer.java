package com.zhhe.lianying_project.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-02-26 15:22
 */
@Component
@RabbitListener(queues = "lyTest")
public class Consumer
{
    public static ConcurrentHashMap<String,JSONObject> signalMap=new ConcurrentHashMap<>();

    @RabbitHandler
    public void process(String msg)
    {
//        System.out.println("RabbitMQ："+msg);
        JSONObject object=JSON.parseObject(msg);
        signalMap.put(object.get("deviceNum").toString(), object);
    }
}
