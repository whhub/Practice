package com.zhhe.lianying_project.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhhe.lianying_project.bean.Signal;
import com.zhhe.lianying_project.bean.entity.*;
import com.zhhe.lianying_project.websocket.MyWebSocket;
import com.zhhe.lianying_project.rabbitmq.Consumer;
import com.zhhe.lianying_project.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/*
 *Author:ZouHeng
 *Des:监听设备状态（开关机时间），更新数据库,WebSocket发送开关机事件列表，在Springboot启动时调用
 *Date:2019-03-04 16:05
 */
@Component
public class ListenEquip implements ApplicationRunner
{
    @Autowired
    private EquipTimeService equipTimeService;
    @Autowired
    private EquipTimeHistoryService equipTimeHistoryService;
    @Autowired
    private StartupEventService startupEventService;
    @Autowired
    private ShutdownEventService shutdownEventService;
    @Autowired
    private SocketService socketService;
    @Autowired
    private RestService restService;
    @Autowired
    private StartupAndShutdownService startupAndShutdownService;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${ws-index4}")
    private String sleepTime4;
    private Integer sleepTimeM;  //线程睡眠时间，单位ms
    private Integer sleepTime;   //线程睡眠时间，单位s

    @Override
    public void run(ApplicationArguments args)
    {
        System.out.println("开始监听设备!");
        sleepTimeM=Integer.parseInt(sleepTime4);
        sleepTime=Integer.parseInt(sleepTime4)/1000;    //线程睡眠时间，单位s
        Thread t = new Thread(() -> {
            String nowTime;
            Integer hour;
            String hourStr;
            String minStr;
            Integer second;
            while (true)
            {
                try
                {
                    //遍历从RabbitMQ接收到的消息
                    for (Map.Entry<String, JSONObject> entry : Consumer.signalMap.entrySet())
                    {
                        Signal signal = JSON.parseObject(entry.getValue().toJSONString(), Signal.class);

                        nowTime = Util.getCurrentTime().split(" ")[1];  //当前时间
                        hour = Integer.parseInt(nowTime.split(":")[0]);  //小时
                        hourStr = nowTime.split(":")[0];  //小时
                        minStr = nowTime.split(":")[1];   //分钟
                        second = Integer.parseInt(nowTime.split(":")[2]);//秒

                        updateHisData(signal,nowTime,hourStr,minStr,second);
                        updateEvent(signal);
                        updateStartupData(hour,minStr,second);
                    }

                    Thread.sleep(sleepTime);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        });
        t.setDaemon(true);
        t.start();
    }

    //更新开机事件和关机事件及相关时间
    private void updateEvent(Signal signal) throws IOException
    {
        boolean exist = equipTimeService.isExist(signal.getDeviceNum());
        if (exist)
        {
            //数据库中存在该设备数据
            EquipTimeToday equipTimeToday = equipTimeService.findByDeviceNum(signal.getDeviceNum());
            Integer stateDb = Util.judgeEquipStateByDatabase(equipTimeToday);
            Integer stateMsg = Util.judgeEquipStateByMsg(signal);
            if (stateDb != stateMsg)
            {
                //存在开关机事件
                if (stateMsg == 0)
                {
                    //关机事件
                    //更新sdd_shutdown_event数据库
                    //转化成json存入
//                    System.out.println("关机事件");
                    Map<String ,String> map = Util.getEquipName(restTemplate);
                    Event event=new Event("关机事件",map.get(signal.getDeviceNum()),signal.getDeviceNum(),Util.getCurrentTime());
                    Long id=shutdownEventService.findAllNum();
                    shutdownEventService.save(new ShutdownEvent(id,JSON.toJSONString(event)));
                    //更新sdd_startup_today数据库
                    Integer diff=Util.calculateTime(equipTimeToday.getStartupTime(), Util.getCurrentTime());
                    equipTimeService.save(new EquipTimeToday(signal.getDeviceNum(), null, Util.getCurrentTime(), equipTimeToday.getTotalTime()+diff));
                }
                else if (stateMsg == 1)
                {
                    //开机事件
                    //更新sdd_startup_event数据库
//                    System.out.println("开机事件");
                    Map<String ,String> map = Util.getEquipName(restTemplate);
                    Event event=new Event("开机事件",map.get(signal.getDeviceNum()),signal.getDeviceNum(),Util.getCurrentTime());
                    Long id=startupEventService.findAllNum();
                    startupEventService.save(new StartupEvent(id,JSON.toJSONString(event)));
                    //更新sdd_startup_today数据库
                    equipTimeService.save(new EquipTimeToday(signal.getDeviceNum(), Util.getCurrentTime(), null, equipTimeToday.getTotalTime()));
                }
                //向前端WebSocket抛出设备开关机时间列表
                String json=socketService.sendStartupAndShutdownList(5);
//                System.out.println("开关机事件数据："+json);
                MyWebSocket.sendInfo(json);
            }
        }
        else
        {
            //数据库中不存在该设备的数据
            Integer stateMsg = Util.judgeEquipStateByMsg(signal);
            if (stateMsg==0)  //关机
            {
                equipTimeService.save(new EquipTimeToday(signal.getDeviceNum(), null, Util.getCurrentTime(), 0));
            }
            else if (stateMsg==1)  //开机
            {
                equipTimeService.save(new EquipTimeToday(signal.getDeviceNum(), Util.getCurrentTime(), null, 0));
            }
        }

    }

    //更新历史数据
    private void updateHisData(Signal signal,String nowTime,String hourStr,String minStr,Integer second)
    {
        if (hourStr.equals("00") && minStr.equals("00") && second <= sleepTime)
        {
//            经过了一天,到达凌晨12点
            Integer stateMsg = Util.judgeEquipStateByMsg(signal);
            //更新历史数据
            EquipTimeHistory equipTimeHistory = new EquipTimeHistory();
            equipTimeHistory.setDeviceNum(signal.getDeviceNum());  //设置设备编号
            String date = nowTime.split(" ")[0];
            String[] dateArray = date.split("-");

            System.out.println("数组越界问题：dateArray.length()="+dateArray.length);
            for (String s:dateArray)
            {
                System.out.println(s);
            }

            Integer index = Integer.parseInt(dateArray[2]) - 1;
            dateArray[2] = index.toString();
            String hisDate = dateArray[0] + "-" + dateArray[1] + "-" + dateArray[2];
            equipTimeHistory.setDate(hisDate);  //设置日期
            if (stateMsg==0)
            {
                //处于关机状态
                EquipTimeToday equipTimeToday = equipTimeService.findByDeviceNum(signal.getDeviceNum());
                equipTimeHistory.setStartupTime(equipTimeToday.getTotalTime()); //设置开机时间
                equipTimeHistory.setShutdownTime(24 * 60 - equipTimeToday.getTotalTime());  //设置关机时间

                // 更新sdd_startup_today数据库
                equipTimeService.save(new EquipTimeToday(signal.getDeviceNum(), null, Util.getZeroDate(Util.getCurrentTime()), 0));
            }
            else
            {
                //处于开机状态
                EquipTimeToday equipTimeToday = equipTimeService.findByDeviceNum(signal.getDeviceNum());
                Integer diff = Util.calculateTime(equipTimeToday.getStartupTime(), (hisDate + " 00:00:00"));
                equipTimeHistory.setStartupTime(equipTimeToday.getTotalTime() + diff); //设置开机时间
                equipTimeHistory.setShutdownTime(24 * 60 - equipTimeToday.getTotalTime() - diff);  //设置关机时间

                // 更新sdd_startup_today数据库
                equipTimeService.save(new EquipTimeToday(signal.getDeviceNum(), Util.getZeroDate(Util.getCurrentTime()), null, 0));
            }
            equipTimeHistoryService.save(equipTimeHistory);
            System.out.println("成功存储历史数据！");
        }
    }

    //更新每个小时的设备开关机数量
    private void updateStartupData(Integer hour,String minStr,Integer second)
    {
        if (minStr.equals("00") && second <= sleepTime)   //整点
        {
            if (hour==0) //凌晨12点，清空数据
            {
                List<StartupAndShutdown> list=startupAndShutdownService.findAll();
                for (int i=0;i<list.size();i++)
                {
                    list.get(i).setStartupNum(0);
                    list.get(i).setShutdownNum(0);
                    startupAndShutdownService.save(list.get(i));
                }
            }
            StartupAndShutdown s=new StartupAndShutdown();
            s.setId(hour+1);
            s.setTime(hour);
            s.setShutdownNum(restService.getShutdownEquipNum());
            s.setStartupNum(restService.getStartupEquipNum());
            startupAndShutdownService.save(s);
        }
    }
}
