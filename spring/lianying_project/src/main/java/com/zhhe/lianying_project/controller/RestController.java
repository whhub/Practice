package com.zhhe.lianying_project.controller;


import com.zhhe.lianying_project.service.RestService;
import com.zhhe.lianying_project.service.ShutdownEventService;
import com.zhhe.lianying_project.service.StartupEventService;
import com.zhhe.lianying_project.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/*
 *Author:ZouHeng
 *Des:Rest接口
 *Date:2019-02-26 14:05
 */
@org.springframework.web.bind.annotation.RestController
public class RestController
{
    @Autowired
    private RestService restService;
    @Autowired
    private StartupEventService startupEventService;
    @Autowired
    private ShutdownEventService shutdownEventService;

    //设备总数
    @RequestMapping("/getTotalEquip")
    public Integer getTotalEquipNum()
    {
        return restService.getTotalEquipNum();
    }

    //设备开机数
    @GetMapping(value = "getStartupEquip")
    public Integer getStartupEquipNum()
    {
        return restService.getStartupEquipNum();
    }

    //设备关机数
    @GetMapping(value = "getShutdownEquip")
    public Integer getShutdownEquipNum()
    {
        return restService.getShutdownEquipNum();
    }

    //开机率
    @GetMapping(value = "getStartupEquipRatio")
    public String getStartupEquipRatio()
    {
        return restService.getStartupEquipRatio();
    }

    /**
     * 获取设备开机时间（可获得当天的，也可以获得历史的数据）
     * @param deviceNum 设备编号
     * @param date 日期（格式：2019-03-04），如果获取当天的数据：不填；如果获取历史数据：填上日期
     * @return 返回开机时间（单位：分）
     */
    @GetMapping(value = "getStartupTime")
    public Integer getStartupTime(String deviceNum, @RequestParam(required = false)String date)
    {
        if (date==null)
        {
//            请求当天的数据
            return restService.getTodayStartupTime(deviceNum);
        }
        else
        {
//            请求历史数据
            return restService.getHisStartupTime(deviceNum, date);
        }
    }

    //设备关机时间
    @GetMapping(value = "getShutdownTime")
    public Integer getShutdownTime(String deviceNum, @RequestParam(required = false)String date)
    {
        String nowDate= Util.getCurrentTime();
        String zeroDate=Util.getZeroDate(nowDate);
        Integer diff=Util.calculateTime(zeroDate,nowDate);
        Integer startupTime=getStartupTime(deviceNum, date);
        return diff-startupTime;
    }

    //设备类别和数量（多少种类，每种多少数量）
    @GetMapping(value = "getEquipSort")
    public String getEquipSort()
    {
        return restService.getEquipSort();
    }

    //开机时刻（事件记录-列表）
    @GetMapping(value = "getStartupList")
    public List<String> getStartupList(Integer num)
    {
        return startupEventService.findEvent(num);
    }

    //关机时刻（事件记录-列表）
    @GetMapping(value = "getShutdownList")
    public List<String> getShutdownList(Integer num)
    {
        return shutdownEventService.findEvent(num);
    }
}
