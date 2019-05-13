package com.zhhe.lianying_project.thread;

import com.zhhe.lianying_project.websocket.WsSend;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-14 15:04
 */
public class ThreadSendThree implements Runnable
{
    private WsSend wsSend;

    public ThreadSendThree(WsSend wsSend)
    {
        this.wsSend = wsSend;
    }

    @Override
    public void run()
    {
        wsSend.send3();
    }
}
