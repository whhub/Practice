package com.zhhe.lianying_project.thread;

import com.zhhe.lianying_project.websocket.WsSend;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-12 11:43
 */
public class ThreadSendTwo implements Runnable
{
    private WsSend wsSend;

    public ThreadSendTwo(WsSend wsSend)
    {
        this.wsSend = wsSend;
    }

    @Override
    public void run()
    {
        wsSend.send2();
    }
}
