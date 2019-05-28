package com.mj.scada.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mj.scada.service.EquipTimeHistoryService;
import com.mj.scada.bean.Signal;
import com.mj.scada.bean.domain.EquipTimeHistory;
import com.mj.scada.bean.domain.EquipTimeToday;
import com.mj.scada.rabbitmq.Consumer;
import com.mj.scada.service.EquipTimeTodayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

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
    private EquipTimeTodayService equipTimeTodayService;
    @Autowired
    private EquipTimeHistoryService equipTimeHistoryService;
    @Value("${my-thread.sleepTime}")
    private String mySleepTime;
    private Integer sleepTimeM;
    private Integer sleepTime;

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

                        updateHisData(signal, Util.getCurrentDateAndTime(), Util.getCurrentYearOrTime("year"), Util.getCurrentYearOrTime("min"), Util.getCurrentYearOrTime("second"));
                        updateTodayData(signal);
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
    private void updateHisData(Signal signal, String nowTime, Integer hour, Integer min, Integer second)
    {
//        经过了一天,到达凌晨12点
        if (hour == 0 && min == 0 && second <= sleepTime)
        {
            System.out.println("时间到达凌晨12点！");
            Integer stateMsg = Util.judgeEquipStateByMsg(signal);
            //更新历史数据
            EquipTimeHistory equipTimeHistory = new EquipTimeHistory();
            equipTimeHistory.setDeviceNum(signal.getDeviceNum());  //设置设备编号
            String date = Util.getCurrentDateOrTime("date");
            String[] dateArray = date.split("-");

            Integer index = Integer.parseInt(dateArray[2]) - 1;
            dateArray[2] = index.toString();
            String hisDate = dateArray[0] + "-" + dateArray[1] + "-" + dateArray[2];
            equipTimeHistory.setDate(hisDate);  //设置日期
            if (stateMsg == 0)
            {
                //处于关机状态
                EquipTimeToday equipTimeToday = equipTimeTodayService.findByDeviceNum(signal.getDeviceNum());
                equipTimeHistory.setStartupTime(equipTimeToday.getTotalTime()); //设置开机时间
                equipTimeHistory.setShutdownTime(24 * 60 - equipTimeToday.getTotalTime());  //设置关机时间

                // 更新sdd_startup_today数据库
                equipTimeTodayService.save(new EquipTimeToday(signal.getDeviceNum(), null, Util.getZeroDate(Util.getCurrentDateAndTime()), 0));
            }
            else
            {
                //处于开机状态
                EquipTimeToday equipTimeToday = equipTimeTodayService.findByDeviceNum(signal.getDeviceNum());
                Integer diff = Util.calculateTime(equipTimeToday.getStartupTime(), (hisDate + " 00:00:00"));
                equipTimeHistory.setStartupTime(equipTimeToday.getTotalTime() + diff); //设置开机时间
                equipTimeHistory.setShutdownTime(24 * 60 - equipTimeToday.getTotalTime() - diff);  //设置关机时间

                // 更新sdd_startup_today数据库
                equipTimeTodayService.save(new EquipTimeToday(signal.getDeviceNum(), Util.getZeroDate(Util.getCurrentDateAndTime()), null, 0));
            }
            equipTimeHistoryService.save(equipTimeHistory);
            System.out.println("成功存储历史数据！");
        }
    }

    //更新当天开机时间数据
    private void updateTodayData(Signal signal)
    {
        boolean exist = equipTimeTodayService.isExist(signal.getDeviceNum());
        if (exist)
        {
            //数据库中存在该设备数据
            EquipTimeToday equipTimeToday = equipTimeTodayService.findByDeviceNum(signal.getDeviceNum());
            Integer stateDb = Util.judgeEquipStateByDatabase(equipTimeToday);
            Integer stateMsg = Util.judgeEquipStateByMsg(signal);
            if (stateDb != stateMsg)    //存在开关机事件
            {
                if (stateMsg == 0)
                {
                    //关机事件
                    Integer diff = Util.calculateTime(equipTimeToday.getStartupTime(), Util.getCurrentDateAndTime());
                    equipTimeTodayService.save(new EquipTimeToday(signal.getDeviceNum(), null, Util.getCurrentDateAndTime(), equipTimeToday.getTotalTime() + diff));
                }
                else if (stateMsg == 1)
                {
                    //开机事件
                    equipTimeTodayService.save(new EquipTimeToday(signal.getDeviceNum(), Util.getCurrentDateAndTime(), null, equipTimeToday.getTotalTime()));
                }
            }
        }
        else
        {
            //数据库中不存在该设备的数据
            Integer stateMsg = Util.judgeEquipStateByMsg(signal);
            if (stateMsg == 0)  //关机
            {
                equipTimeTodayService.save(new EquipTimeToday(signal.getDeviceNum(), null, Util.getCurrentDateAndTime(), 0));
            }
            else if (stateMsg == 1)  //开机
            {
                equipTimeTodayService.save(new EquipTimeToday(signal.getDeviceNum(), Util.getCurrentDateAndTime(), null, 0));
            }
        }
    }
}
