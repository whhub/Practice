package com.zhhe.lianying_project.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhhe.lianying_project.bean.WebSocketMsg5;
import com.zhhe.lianying_project.service.*;
import com.zhhe.lianying_project.thread.ThreadManager;
import com.zhhe.lianying_project.thread.ThreadSendThree;
import com.zhhe.lianying_project.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/*
 *Author:ZouHeng
 *Des:设备实时信息（index=1），调用controller接口时发送；
 * 开机数量等（index=2），调用controller接口时发送；
 * 开关机列表（index=4），开关机事件时调用（ListenEquip中）；
 * 整点开关机数量（index=3），WebSocket连接时整点发送
 *Date:2019-02-19 15:21
 */
@ServerEndpoint(value = "/websocket")
@Component
public class MyWebSocket
{
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    public static MyWebSocket myWebSocket;
    @Autowired
    private SocketService socketService;
    @Autowired
    private RestService restService;
    @Autowired
    private WsSend wsSend;
    @Autowired
    private StartupAndShutdownService startupAndShutdownService;
    @Value("${ws-index3}")
    private String sleepTime3;

    public MyWebSocket()
    {
    }

    @PostConstruct
    public void init()
    {
        System.out.println("MyWebSocket:init()");
        myWebSocket = this;
        myWebSocket.socketService = this.socketService;
        myWebSocket.restService = this.restService;
        myWebSocket.wsSend = this.wsSend;
        myWebSocket.startupAndShutdownService = this.startupAndShutdownService;
        myWebSocket.sleepTime3 = this.sleepTime3;
    }


    //连接建立成功调用的方法
    @OnOpen
    public void onOpen(Session session)
    {
        this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        System.out.println("onOpen:有新连接加入！当前在线人数为" + getOnlineCount());
        try
        {
//            sendMessage("连接成功");
            //设备类别和数量信息
            Map<String, Integer> map = (Map) JSONObject.parse(myWebSocket.restService.getEquipSort());
            WebSocketMsg5 msg5 = new WebSocketMsg5();
            msg5.setIndex("5");
            msg5.setMsg(map);
            sendInfo(JSON.toJSONString(msg5));
            //开关机事件列表(返回5组数据)
            String json = myWebSocket.socketService.sendStartupAndShutdownList(5);
            sendInfo(json);
            //整点开机数据（WebSocket刚连接时，会发一次该数据）
            Integer hour = Integer.parseInt(Util.getCurrentTime().split(" ")[1].split(":")[0]);
            sendInfo(myWebSocket.startupAndShutdownService.selectNum(hour));
//            System.out.println(myWebSocket.startupAndShutdownService.selectNum(hour));
            //开启线程，每到整点发一次整点开机数据
            ThreadSendThree three=new ThreadSendThree(myWebSocket.wsSend);
            Integer time3=Integer.parseInt(myWebSocket.sleepTime3);
            //先清除任务
            if (ThreadManager.webTaskFuture!=null)
            {
                ThreadManager.webTaskFuture.cancel(true);
                ThreadManager.webTaskFuture=null;
            }
            ThreadManager.webTaskFuture=ThreadManager.executorPool.scheduleAtFixedRate(three, 0, time3, TimeUnit.MILLISECONDS);
        } catch (IOException e)
        {
            System.out.println("IO异常");
        }
    }

    //连接关闭调用的方法
    @OnClose
    public void onClose()
    {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        System.out.println("onClose:有一连接关闭！当前在线人数为" + getOnlineCount());
        if (ThreadManager.webTaskFuture!=null)
        {
            ThreadManager.webTaskFuture.cancel(true);
            ThreadManager.webTaskFuture=null;
        }

    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException
    {
//        System.out.println("onMessage:来自客户端的消息:" + message);
//        session.getBasicRemote().sendText("pong");
        sendMessage("pong");

        //群发消息
//        for (MyWebSocket item : webSocketSet)
//        {
//            try
//            {
//                item.sendMessage(message);
//            } catch (IOException e)
//            {
//                e.printStackTrace();
//            }
//        }
    }

    //发生错误时调用
    @OnError
    public void onError(Session session, Throwable error)
    {
        System.out.println("onError:WebSocket发生错误");
        if (ThreadManager.webTaskFuture!=null)
        {
            ThreadManager.webTaskFuture.cancel(true);
            ThreadManager.webTaskFuture=null;
        }
        System.out.println(error.getMessage());
//        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException
    {
        //实现服务器主动推送
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }

    //群发自定义消息
    public static void sendInfo(String message) throws IOException
    {
        for (MyWebSocket item : webSocketSet)
        {
            try
            {
                item.sendMessage(message);
            } catch (IOException e)
            {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount()
    {
        return onlineCount;
    }

    public static synchronized void addOnlineCount()
    {
        MyWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount()
    {
        MyWebSocket.onlineCount--;
    }
}
