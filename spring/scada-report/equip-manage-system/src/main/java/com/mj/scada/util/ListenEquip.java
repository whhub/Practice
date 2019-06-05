package com.mj.scada.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mj.scada.service.EquipService;
import com.mj.scada.service.TimeHistoryService;
import com.mj.scada.bean.Signal;
import com.mj.scada.bean.domain.TimeHistory;
import com.mj.scada.bean.domain.TimeToday;
import com.mj.scada.rabbitmq.Consumer;
import com.mj.scada.service.TimeTodayService;
import com.mj.scada.service.FaultEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/*
 *Author:ZouHeng
 *Des:存储开机时间
 *Date:2019-04-03 10:10
 */
@Component
public class ListenEquip implements ApplicationRunner
{
    @Autowired
    private TimeTodayService timeTodayService;
    @Autowired
    private TimeHistoryService timeHistoryService;
    @Autowired
    private EquipService equipService;
    @Autowired
    private FaultEventService faultEventService;
    @Value("${my-thread.sleepTime}")
    private String mySleepTime;
    private Integer sleepTimeM;
    private Integer sleepTime;

    private static Map<String,String> faultOldMap=new HashMap<>();

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        System.out.println("开始监听设备(ListenEquip/run)！！！");
        //从Rest接口同步设备通用信息，并存入数据库

        sleepTimeM = Integer.parseInt(mySleepTime);
        sleepTime = Integer.parseInt(mySleepTime) / 1000;    //线程睡眠时间，单位s
        Thread thread = new Thread(() -> {
            while (true)
            {
                if (Consumer.signalMap!=null&&Consumer.signalMap.size()!=0)
                {
                    //遍历从RabbitMQ接收到的消息
                    for (Map.Entry<String, JSONObject> entry : Consumer.signalMap.entrySet())
                    {
                        Signal signal = JSON.parseObject(entry.getValue().toJSONString(), Signal.class);

                        updateHisData(signal, Util.getCurrentYearOrTime("hour"), Util.getCurrentYearOrTime("min"), Util.getCurrentYearOrTime("second"));
                        updateTodayData(signal);

                        //统计故障事件
                        updateFaultEvent(signal);
                    }
                }
                try
                {
                    Thread.sleep(sleepTimeM);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();


    }

    //更新历史开机时间数据
    private void updateHisData(Signal signal, Integer hour, Integer min, Integer second)
    {
//        经过了一天,到达凌晨12点
        if (hour == 0 && min == 0 && second <= sleepTime)
        {
            System.out.println("时间到达凌晨12点！");
            Integer stateMsg = equipService .judgeEquipStateByMsg(signal);
            //更新历史数据
            TimeHistory timeHistory = new TimeHistory();
            timeHistory.setDeviceNum(signal.getDeviceNum());  //设置设备编号
            String date = Util.getCurrentDateOrTime("date");
            String[] dateArray = date.split("-");

            Integer index = Integer.parseInt(dateArray[2]) - 1;
            dateArray[2] = index.toString();
            String hisDate = dateArray[0] + "-" + dateArray[1] + "-" + dateArray[2];
            timeHistory.setDate(hisDate);  //设置日期
            if (stateMsg == 0)
            {
                //处于关机状态
                TimeToday equipTimeToday = timeTodayService.findByDeviceNum(signal.getDeviceNum());
                timeHistory.setOpenTime(equipTimeToday.getTotalTime()); //设置开机时间
                timeHistory.setCloseTime(24 * 60 - equipTimeToday.getTotalTime());  //设置关机时间

                // 更新sdd_startup_today数据库
                timeTodayService.save(new TimeToday(signal.getDeviceNum(), null, Util.getZeroDate(Util.getCurrentDateAndTime()), 0));
            }
            else
            {
                //处于开机状态
                TimeToday equipTimeToday = timeTodayService.findByDeviceNum(signal.getDeviceNum());
                Integer diff = Util.calculateTime(equipTimeToday.getStartupTime(), (hisDate + " 00:00:00"));
                timeHistory.setOpenTime(equipTimeToday.getTotalTime() + diff); //设置开机时间
                timeHistory.setCloseTime(24 * 60 - equipTimeToday.getTotalTime() - diff);  //设置关机时间

                // 更新sdd_startup_today数据库
                timeTodayService.save(new TimeToday(signal.getDeviceNum(), Util.getZeroDate(Util.getCurrentDateAndTime()), null, 0));
            }
            timeHistoryService.save(timeHistory);
            System.out.println("成功存储历史数据！");
        }
    }

    //更新当天开机时间数据
    private void updateTodayData(Signal signal)
    {
        boolean exist = timeTodayService.isExist(signal.getDeviceNum());
        if (exist)
        {
            //数据库中存在该设备数据
            TimeToday equipTimeToday = timeTodayService.findByDeviceNum(signal.getDeviceNum());
            Integer stateDb = Util.judgeEquipStateByDatabase(equipTimeToday);
            Integer stateMsg = equipService.judgeEquipStateByMsg(signal);
            if (stateDb != stateMsg)    //存在开关机事件
            {
                if (stateMsg == 0)
                {
                    //关机事件
                    Integer diff = Util.calculateTime(equipTimeToday.getStartupTime(), Util.getCurrentDateAndTime());
                    timeTodayService.save(new TimeToday(signal.getDeviceNum(), null, Util.getCurrentDateAndTime(), equipTimeToday.getTotalTime() + diff));
                }
                else if (stateMsg == 1)
                {
                    //开机事件
                    timeTodayService.save(new TimeToday(signal.getDeviceNum(), Util.getCurrentDateAndTime(), null, equipTimeToday.getTotalTime()));
                }
            }
        }
        else
        {
            //数据库中不存在该设备的数据
            Integer stateMsg = equipService.judgeEquipStateByMsg(signal);
            if (stateMsg == 0)  //关机
            {
                timeTodayService.save(new TimeToday(signal.getDeviceNum(), null, Util.getCurrentDateAndTime(), 0));
            }
            else if (stateMsg == 1)  //开机
            {
                timeTodayService.save(new TimeToday(signal.getDeviceNum(), Util.getCurrentDateAndTime(), null, 0));
            }
        }
    }

    //更新故障事件
    private void updateFaultEvent(Signal signal)
    {
        if (signal.getData().getState()!=null)
        {
            Map<String,String> stateMap=signal.getData().getState();
            if (stateMap.get("fault")!=null)
            {
                if (!faultOldMap.containsKey(signal.getDeviceNum()))
                {
                    faultOldMap.put(signal.getDeviceNum(), stateMap.get("fault"));
                    if (stateMap.get("fault").equals("1"))  //出现故障
                    {
                        startFault(signal);
                    }
                }
                else
                {
                    if (!stateMap.get("fault").equals(faultOldMap.get(signal.getDeviceNum())))
                    {
                        //状态变化
                        if (stateMap.get("fault").equals("0"))
                        {
                            //故障修好
                            faultEventService.eventEnd(signal.getDeviceNum());
                        }
                        else if (stateMap.get("fault").equals("1"))
                        {
                            //出现故障
                            startFault(signal);
                        }
                    }
                    else
                    {
                        //状态无变化
                        if (faultOldMap.get(signal.getDeviceNum()).equals("1"))
                        {
                            //设备一直处于故障状态
                            //通宵故障
                            Integer hour=Util.getCurrentYearOrTime("hour");
                            Integer min=Util.getCurrentYearOrTime("min");
                            Integer second=Util.getCurrentYearOrTime("second");
                            if (hour == 23 && min == 59 && second >= 60-sleepTime)
                            {
                                faultEventService.eventEnd(signal.getDeviceNum());
                            }
                            if (hour==0&&min==0&&second<=sleepTime)
                            {
                                String faultCode=null;
                                if (stateMap.get("faultCode")!=null)
                                {
                                    faultCode=stateMap.get("faultCode");
                                }
                                faultEventService.eventStart(signal.getDeviceNum(), faultCode);
                            }
                        }
                    }
                }
            }
        }
    }

    //出现故障，存储数据库
    private void startFault(Signal signal)
    {
        Map<String,String> stateMap=signal.getData().getState();
        String faultCode=null;
        if (stateMap.get("faultCode")!=null)
        {
            faultCode=stateMap.get("faultCode");
        }
        faultEventService.eventStart(signal.getDeviceNum(),faultCode);
    }
}
