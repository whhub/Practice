package com.mj.scada.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mj.scada.service.EquipDataService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-02-26 15:22
 */
@Component
@RabbitListener(queues = "equipInfoTest")
public class Consumer
{
    public static ConcurrentHashMap<String,JSONObject> signalMap=new ConcurrentHashMap<>();
    @Autowired
    private EquipDataService equipDataService;

    /**
     * {"deviceNum":"2019040901548220283D","date":"2019-05-15 11:57:00:115","data":{"process":{},"custom":{},"state":{}}}
     * @param msg
     */
    @RabbitHandler
    public void process(String msg)
    {
//        System.out.println("RabbitMQ："+msg);
        JSONObject object=JSON.parseObject(msg);
        signalMap.put(object.get("deviceNum").toString(), object);

        //将数据更新到数据库
        equipDataService.saveData(msg);
    }
}
