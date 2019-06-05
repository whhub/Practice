package com.mj.scada.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mj.scada.bean.Signal;
import com.mj.scada.bean.domain.TimeToday;
import com.mj.scada.rabbitmq.Consumer;
import com.mj.scada.repository.TimeTodayRepository;
import com.mj.scada.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-04-02 18:10
 */
@Service
public class TimeTodayServiceImpl implements TimeTodayService
{
    @Autowired
    private TimeTodayRepository repository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private EquipService equipService;

    //判断是否存在记录
    @Override
    public boolean isExist(String deviceNum)
    {
        TimeToday equipTimeToday = repository.findByDeviceNum(deviceNum);
        if (equipTimeToday == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    public TimeToday findByDeviceNum(String deviceNum)
    {
        return repository.findByDeviceNum(deviceNum);
    }

    @Override
    public void save(TimeToday today)
    {
        repository.save(today);
    }

    //获取当天当前时刻的开机时间
    @Override
    public Integer getTodayStartupTime(String deviceNum)
    {
        JSONObject obj = Consumer.signalMap.get(deviceNum);
        if (obj!=null)
        {
            Signal signal = JSON.parseObject(obj.toJSONString(), Signal.class);
            Integer state = equipService.judgeEquipStateByMsg(signal);
            TimeToday e = repository.findByDeviceNum(signal.getDeviceNum());
            if (state == 0)
            {
                //设备处于关机状态
                return e.getTotalTime();
            }
            else
            {
                //设备处于开机状态
                Integer diff = Util.calculateTime(e.getStartupTime(), Util.getCurrentDateAndTime());
                return e.getTotalTime() + diff;
            }
        }
        else
        {
            return 0;
        }

    }

    //获取当天当前时刻的关机时间
    @Override
    public Integer getTodayCloseTime(String deviceNum)
    {
        Integer startupTime = getTodayStartupTime(deviceNum);
        String nowTime = Util.getCurrentDateAndTime();
        String zeroTime = Util.getZeroDate(nowTime);
        Integer totalTime = Util.calculateTime(zeroTime, nowTime);
        return totalTime-startupTime;
    }

    //获取所有设备的开机时间
    @Override
    public Map<String, String> getAllTodayStartupTime()
    {
        //获取所有设备的开机时间
        Map<String, String> map = new HashMap<>();
        for (Map.Entry<String, String> entry : equipService.getEquipNameMap().entrySet())
        {
            map.put(entry.getKey(), getTodayStartupTime(entry.getKey()).toString());
        }
        return map;
    }
}
