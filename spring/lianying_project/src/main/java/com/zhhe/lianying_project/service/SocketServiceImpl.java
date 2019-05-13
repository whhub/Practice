package com.zhhe.lianying_project.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhhe.lianying_project.bean.*;
import com.zhhe.lianying_project.bean.entity.EquipTimeToday;
import com.zhhe.lianying_project.thread.ThreadManager;
import com.zhhe.lianying_project.thread.ThreadSendOne;
import com.zhhe.lianying_project.thread.ThreadSendTwo;
import com.zhhe.lianying_project.websocket.MyWebSocket;
import com.zhhe.lianying_project.rabbitmq.Consumer;
import com.zhhe.lianying_project.util.Util;
import com.zhhe.lianying_project.websocket.WsSend;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-01 17:50
 */
@Service
public class SocketServiceImpl implements SocketService
{
    @Autowired
    private StartupEventService startupEventService;
    @Autowired
    private ShutdownEventService shutdownEventService;

    @Autowired
    private WsSend wsSend;
    @Value("${ws-index1}")
    private String sleepTime1;
    @Value("${ws-index2}")
    private String sleepTime2;

    //返回设备开关机事件列表的json
    @Override
    public String sendStartupAndShutdownList(Integer num)
    {
        StartupAndShutdownList s = new StartupAndShutdownList();
        s.setIndex("4");
        List<JSONObject> jsonList1=startupEventService.findEventObj(num);
        List<JSONObject> jsonList2=shutdownEventService.findEventObj(num);
        List<Map<String,String>> startupList=new ArrayList<>();
        List<Map<String,String>> shutdownList=new ArrayList<>();
        for (JSONObject obj:jsonList1)
        {
            Map<String,String> startupMap=new HashMap<>();
            startupMap.put("deviceNum", obj.getString("deviceNum"));
            startupMap.put("equipName", obj.getString("equipName"));
            startupMap.put("startupTime", obj.getString("time"));
            startupList.add(startupMap);
        }
        for (JSONObject obj:jsonList2)
        {
            Map<String,String> shutdownMap=new HashMap<>();
            shutdownMap.put("deviceNum", obj.getString("deviceNum"));
            shutdownMap.put("equipName", obj.getString("equipName"));
            shutdownMap.put("shutdownTime", obj.getString("time"));
            shutdownList.add(shutdownMap);
        }
        s.setStartupData(startupList);
        s.setShutdownData(shutdownList);
        String json=JSON.toJSONString(s);
        return json;
    }

    @Override
    public void sendMsg()
    {
        if (ThreadManager.taskFutureList!=null&&!ThreadManager.taskFutureList.isEmpty())
        {
            for (ScheduledFuture<?> s: ThreadManager.taskFutureList)
            {
                s.cancel(true);
            }
            ThreadManager.taskFutureList.clear();
        }

        Integer time1=Integer.parseInt(sleepTime1);
        Integer time2=Integer.parseInt(sleepTime2);
        ThreadSendOne one = new ThreadSendOne(wsSend);
        ScheduledFuture<?> taskFuture1 =ThreadManager.executorPool.scheduleAtFixedRate(one, 0, time1, TimeUnit.MILLISECONDS);
        ThreadSendTwo two = new ThreadSendTwo(wsSend);
        ScheduledFuture<?> taskFuture2 =ThreadManager.executorPool.scheduleAtFixedRate(two, 0, time2, TimeUnit.MILLISECONDS);
        ThreadManager.taskFutureList.add(taskFuture1);
        ThreadManager.taskFutureList.add(taskFuture2);
    }

}
