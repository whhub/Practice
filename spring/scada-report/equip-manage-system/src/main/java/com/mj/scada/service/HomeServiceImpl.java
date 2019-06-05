package com.mj.scada.service;

import com.mj.scada.bean.json.ReturnData;
import com.mj.scada.bean.json.StatusInfoJson;
import com.mj.scada.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-05-23 11:09
 */
@Service
public class HomeServiceImpl implements HomeService
{
    @Autowired
    private EquipService equipService;
    @Autowired
    private TimeTodayService timeTodayService;

    @Override
    public ReturnData status()
    {
        Integer openNum = 0, closeNum = 0, faultNum = 0;   //开机、关机、故障
        //遍历从RabbitMQ接收到的消息
        Map<String, Integer> startupMap = equipService.getEquipsStartup();
        Map<String, Integer> faultMap = equipService.getEquipsFault();
        for (Map.Entry<String, Integer> entry : startupMap.entrySet())
        {
            if (entry.getValue() == 0)
            {
                closeNum++;
            }
            else
            {
                openNum++;
            }
        }
        for (Map.Entry<String, Integer> entry : faultMap.entrySet())
        {
            if (entry.getValue() == 1)
            {
                faultNum++;
            }
        }
        Map<String, Integer> map = new HashMap<>();
        map.put("开机", openNum);
        map.put("关机", closeNum);
        map.put("故障", faultNum);
        return new ReturnData(0, "成操作功", map);
    }

    @Override
    public ReturnData types()
    {
        Map<String, Integer> map = equipService.getEquipSort();
        return new ReturnData(0, "成操作功", map);
    }

    @Override
    public ReturnData statusInfo()
    {
        Map<String,String> equipNameMap=equipService.getEquipNameMap();
        Map<String,Integer> equipsStartupMap=equipService.getEquipsStartup();
        Map<String,Integer> equipsFaultMap=equipService.getEquipsFault();
        List<StatusInfoJson> list = new ArrayList<>();
        for (Map.Entry<String,String> entry:equipNameMap.entrySet())
        {
            Integer openTimeNow= timeTodayService.getTodayStartupTime(entry.getKey());
            Integer totalTime=Util.calculateTime(Util.getCurrentDateAndTime(), Util.getZeroDate(Util.getCurrentDateAndTime()));
            String openRatio=((Integer)(openTimeNow/totalTime)*100)+"%";
            Integer startup=equipsStartupMap.get(entry.getKey());
            Integer fault=equipsFaultMap.get(entry.getKey());
            Integer statusCode=0;
            if (startup==0)
            {
                statusCode=0;   //关机
            }
            else
            {
                if (fault==0)
                {
                    //无故障
                    statusCode=1;   //开机
                }
                else
                {
                    //有故障
                    statusCode=2;   //故障
                }
            }
            StatusInfoJson statusInfoJson=new StatusInfoJson(entry.getValue(),entry.getKey(),Util.getCurrentDateAndTime(),true,openTimeNow,openRatio,statusCode);
            list.add(statusInfoJson);
        }

        return new ReturnData(0,"操作成功",list);
    }

    @Override
    public ReturnData startup()
    {
        Map<String,Integer> map = new HashMap<>();
        Map<String,Integer> startupMap = equipService.getEquipsStartup();
        Map<String,String> sortMap=equipService.getEquipSortMap();

        Map<String,Integer> equipSortMap=equipService.getEquipSort();
        List<String> sorts=new ArrayList<>();
        for (Map.Entry<String,Integer> entry:equipSortMap.entrySet())
        {
            sorts.add(entry.getKey());
        }

        for (String s:sorts)
        {
            Integer total=0;
            Integer open=0;
            for (Map.Entry<String,String> entry:sortMap.entrySet())
            {
                if (entry.getValue().equals(s))
                {
                    total++;
                    if (startupMap.get(entry.getKey())==1)
                    {
                        open++;
                    }
                }
            }
            Integer openRatio=0;
            if (total!=0)
            {
                openRatio=open/total*100;
            }
            else
            {
                openRatio=0;
            }
//            System.out.println("open = "+open);
//            System.out.println("total = "+total);
            map.put(s, openRatio);
        }
        return new ReturnData(0,"操作成功",map);
    }
}
