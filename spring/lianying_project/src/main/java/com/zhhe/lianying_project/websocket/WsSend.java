package com.zhhe.lianying_project.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhhe.lianying_project.bean.Signal;
import com.zhhe.lianying_project.bean.WebSocketMsg;
import com.zhhe.lianying_project.bean.WebSocketMsgChild;
import com.zhhe.lianying_project.rabbitmq.Consumer;
import com.zhhe.lianying_project.service.RestService;
import com.zhhe.lianying_project.service.StartupAndShutdownService;
import com.zhhe.lianying_project.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *Author:ZouHeng
 *Des:WebSocket发送数据函数实现
 *Date:2019-03-12 10:56
 */
@Component
public class WsSend
{
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RestService restService;
    @Autowired
    private StartupAndShutdownService startupAndShutdownService;
    @Value("${ws-index3}")
    private String sleepTime3;


    private Signal signal;
    private Map<String,String> map;
    private WebSocketMsg data;
    List<WebSocketMsgChild> list = new ArrayList<>();


    //发送第一块数据
    public void send1()
    {
        data=new WebSocketMsg();
        data.setIndex("1");
        for (Map.Entry<String, JSONObject> entry : Consumer.signalMap.entrySet())
        {
            signal = JSON.parseObject(entry.getValue().toJSONString(), Signal.class);
            WebSocketMsgChild child = new WebSocketMsgChild();
            child.setDeviceNum(signal.getDeviceNum());
            child.setName(Util.getEquipName(restTemplate).get(signal.getDeviceNum()));
            child.setData(signal.getData());
            list.add(child);
        }
        data.setMsg(list);
        try
        {
//            System.out.println("第一块数据："+JSON.toJSONString(data));
            MyWebSocket.sendInfo(JSON.toJSONString(data));
            list.clear();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void send2()
    {
        map = new HashMap<>();
        map.put("index", "2");
        map.put("startupNum", restService.getStartupEquipNum().toString());
        map.put("shutdownNum", restService.getShutdownEquipNum().toString());
        map.put("startupRatio", restService.getStartupEquipRatio());
        try
        {
//            System.out.println("第二块数据："+JSON.toJSONString(map));
            MyWebSocket.sendInfo(JSON.toJSONString(map));
            map.clear();
            map=null;
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    //整点开关机数量
    public void send3()
    {
        Integer time3=Integer.parseInt(sleepTime3);
        String nowTime= Util.getCurrentTime().split(" ")[1];  //当前时间
        Integer hour = Integer.parseInt(nowTime.split(":")[0]);  //小时
        String min = nowTime.split(":")[1];   //分钟
        Integer second = Integer.parseInt(nowTime.split(":")[2]);//秒
        if (min.equals("00") && second <= time3)   //整点
        {
            try
            {
                MyWebSocket.sendInfo(startupAndShutdownService.selectNum(hour));
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
