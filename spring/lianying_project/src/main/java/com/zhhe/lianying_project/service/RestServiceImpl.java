package com.zhhe.lianying_project.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhhe.lianying_project.bean.*;
import com.zhhe.lianying_project.bean.entity.EquipTimeHistory;
import com.zhhe.lianying_project.bean.entity.EquipTimeToday;
import com.zhhe.lianying_project.rabbitmq.Consumer;
import com.zhhe.lianying_project.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-04 9:21
 */
@Service
public class RestServiceImpl implements RestService
{
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private EquipTimeService equipTimeService;
    @Autowired
    private EquipTimeHistoryService equipTimeHistoryService;

    //设备总数
    @Override
    public Integer getTotalEquipNum()
    {
        JSONObject jsonObject= Util.getRest(ConfigBean.equipListUrl,restTemplate);
        String json = jsonObject.toJSONString();
        RestEquipList restEquipList = JSON.parseObject(json, RestEquipList.class);
        return restEquipList.getData().size();
    }

    //设备开机数
    @Override
    public Integer getStartupEquipNum()
    {
        Integer num = 0;
        for (Map.Entry<String, JSONObject> entry : Consumer.signalMap.entrySet())
        {
            Signal signal = JSON.parseObject(entry.getValue().toJSONString(), Signal.class);

            if (signal.getData().getState()!=null)
            {
                if (signal.getData().getState().containsKey("startup"))
                {
                    if (signal.getData().getState().get("startup").equals("1"))
                    {
                        num++;
                    }
                }
            }
        }
        return num;
    }

    //设备关机数
    @Override
    public Integer getShutdownEquipNum()
    {
        Integer total=getTotalEquipNum();
        Integer startup=getStartupEquipNum();
        return total-startup;
    }

    //开机率
    @Override
    public String getStartupEquipRatio()
    {
        Integer start=getStartupEquipNum();
        Integer total=getTotalEquipNum();
        float ratioLong=(float)start/total*100;
        Integer ratio=(int)ratioLong;
        return ratio+"%";
    }

    //获取当天当前时刻的开机时间
    @Override
    public Integer getTodayStartupTime(String deviceNum)
    {
        JSONObject obj=Consumer.signalMap.get(deviceNum);
        Signal signal = JSON.parseObject(obj.toJSONString(),Signal.class);
        Integer state= Util.judgeEquipStateByMsg(signal);
        EquipTimeToday e=equipTimeService.findByDeviceNum(signal.getDeviceNum());
        if (state==0)
        {
            //设备处于关机状态
            return e.getTotalTime();
        }
        else
        {
            //设备处于开机状态
            Integer diff=Util.calculateTime(e.getStartupTime(), Util.getCurrentTime());
            return e.getTotalTime()+diff;
        }
    }

    //获取历史开机时间
    @Override
    public Integer getHisStartupTime(String deviceNum, String date)
    {
        EquipTimeHistory equipTimeHistory=equipTimeHistoryService.findByDeviceNumAndDate(deviceNum, date);
        return equipTimeHistory.getStartupTime();
    }

    @Override
    public String getEquipSort()
    {
        JSONObject obj=Util.getRest(ConfigBean.equipSortUrl,restTemplate);
        EquipSort equipSort=JSON.parseObject(obj.toJSONString(),EquipSort.class);
//        System.out.println(equipSort);
        Map<String,Integer> map = new HashMap<>();
        for (Map<String,List<Map<String, List<RestEquipListData>>>> k:equipSort.getData())
        {
            for (Map.Entry<String,List<Map<String, List<RestEquipListData>>>> m:k.entrySet())
            {
                for (Map<String, List<RestEquipListData>> s:m.getValue())
                {
                    for (Map.Entry<String,List<RestEquipListData>> entry:s.entrySet())
                    {
//                        System.out.println(entry.getKey());
                        if (map.containsKey(entry.getKey()))
                        {
                            //已包含该类，数量加1
                            Integer num=map.get(entry.getKey())+entry.getValue().size();
                            map.put(entry.getKey(),num);
                        }
                        else
                        {
                            //不包含该类别
                            map.put(entry.getKey(), entry.getValue().size());
                        }
                    }
                }
            }
        }
        String json=JSON.toJSONString(map);
        return json;
    }


}
