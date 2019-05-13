package com.zhhe.lianying_project.thread;

import com.zhhe.lianying_project.websocket.WsSend;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

/*
 *Author:ZouHeng
 *Des:发送WebSocket第一块数据的线程
 *Date:2019-03-12 11:00
 */
public class ThreadSendOne implements Runnable
{
    private WsSend wsSend;

    public ThreadSendOne(WsSend wsSend)
    {
        this.wsSend=wsSend;
    }

    @Override
    public void run()
    {
//        System.out.println("Thread1开始运行！");
        wsSend.send1();
    }
}
