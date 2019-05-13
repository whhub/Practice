package com.zhhe.lianying_project.controller;

import com.zhhe.lianying_project.bean.Data;
import com.zhhe.lianying_project.bean.RestEquipListData;
import com.zhhe.lianying_project.service.SocketService;
import com.zhhe.lianying_project.thread.ThreadManager;
import com.zhhe.lianying_project.thread.ThreadSendOne;
import com.zhhe.lianying_project.thread.ThreadSendTwo;
import com.zhhe.lianying_project.websocket.WsSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-02-19 17:46
 */
@Controller
public class SocketController
{
    @Autowired
    private SocketService socketService;
    @Value("${reconnectionTime}")
    private String reconnectionTime;
    @Value("${reconnectionMaxNum}")
    private String reconnectionMaxNum;
    @Value("${logoUrl}")
    private String logoUrl;

    @RequestMapping("index")
    public String index(Model model)
    {
        System.out.println("SocketController-sendMsgToWebsocket：实时向WebSocket发送消息！");

        //将配置文件参数传给前端
//        System.out.println("reconnectionTime = "+reconnectionTime+" ; reconnectionMaxNum = "+reconnectionMaxNum+" ; wsUrl = "+wsUrl);
        Integer reTime=Integer.parseInt(reconnectionTime);
        Integer reMaxNum=Integer.parseInt(reconnectionMaxNum);
        model.addAttribute("max", reMaxNum);     //断线重连最大次数
        model.addAttribute("reTime", reTime);    //断线重连时间
        model.addAttribute("logoUrl", logoUrl);  //logo路径

        socketService.sendMsg();
        return "index";
    }

    @RequestMapping("socket")
    public String socket()
    {
        return "socket";
    }
}
