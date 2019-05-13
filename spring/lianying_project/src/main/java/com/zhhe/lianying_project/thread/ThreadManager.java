package com.zhhe.lianying_project.thread;

import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import javax.websocket.Session;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-07 11:28
 */
@Component
public class ThreadManager
{
    public static ScheduledExecutorService executorPool;
    public static List<ScheduledFuture<?>> taskFutureList;
    public static ScheduledFuture<?> webTaskFuture;  //用于MyWebSocket中发送index=3的数据

    static
    {
        int ncpu = Runtime.getRuntime().availableProcessors();
        executorPool = Executors.newScheduledThreadPool(5);

        taskFutureList = new ArrayList<>();
    }

    //在程序停止时，会自动调用该注解下的函数
    @PreDestroy
    public static void destroy()
    {
        executorPool.shutdownNow();
        taskFutureList.clear();
//        threadList.forEach(Thread::interrupt);
    }
}
