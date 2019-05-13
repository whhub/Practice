package com.zhhe.lianying_project.controller;

import com.zhhe.lianying_project.thread.ThreadSendOne;
import com.zhhe.lianying_project.thread.ThreadSendTwo;
import com.zhhe.lianying_project.websocket.MyWebSocket;
import com.zhhe.lianying_project.service.EquipTimeService;
import com.zhhe.lianying_project.service.SocketService;
import com.zhhe.lianying_project.service.StartupEventService;
import com.zhhe.lianying_project.thread.ThreadManager;
import com.zhhe.lianying_project.websocket.WsSend;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-04 15:37
 */
@RestController
public class TestController
{
    @Autowired
    private StartupEventService startupEventService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private EquipTimeService equipTimeService;
    @Autowired
    private SocketService socketService;
    @Value("${ws-index1}")
    private String sleepTime1;
    @Value("${ws-index2}")
    private String sleepTime2;

    @GetMapping(value = "test")
    public void test()
    {
//        startupEventService.save(new StartupEvent("开机事件，deviceNum为"+"jgisdjigjsdigjero"+"的设备在"+ Util.getCurrentTime()+"开机"));

        List<String> list = startupEventService.findEvent(10);
        System.out.println(list.size());
        for (String s : list)
        {
            System.out.println(s);
        }
    }

    @GetMapping(value = "test1")
    public String test1(int... a)
    {
        System.out.println(a);
        System.out.println(a);
        return "succ";
    }

    @GetMapping(value = "test2")
    public String test2(@RequestParam(required = false) String a, @RequestParam(required = false) String b)
    {
        System.out.println(a);
        System.out.println(b);
        return "succ";
    }

}
