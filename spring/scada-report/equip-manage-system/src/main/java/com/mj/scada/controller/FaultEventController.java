package com.mj.scada.controller;

import com.mj.scada.bean.json.ReturnData;
import com.mj.scada.log.MyLog;
import com.mj.scada.service.FaultEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-05-29 16:02
 */
@RestController
@RequestMapping("faultEvent")
public class FaultEventController
{
    @Autowired
    private FaultEventService faultEventService;

    //返回时间区间的故障事件列表
    @MyLog(value = "获取时间区间的故障事件列表")
    @RequestMapping("sectionList")
    public ReturnData sectionList(String deviceNum,String startDate, String endDate) throws ParseException
    {
        return faultEventService.sectionList(deviceNum,startDate, endDate);
    }

    //返回当天的事件列表
    @MyLog(value = "获取当天的事件列表")
    @RequestMapping("todayList")
    public ReturnData todayList(String deviceNum)
    {
        return faultEventService.todayList(deviceNum);
    }

    //返回这周或上周每天的故障总时间
    @MyLog(value = "获取本周或上周每天的故障总时间")
    @RequestMapping("weekTotal")
    public ReturnData weekTotal(String deviceNum,Integer index)
    {
        return faultEventService.weekTotal(deviceNum,index);
    }

    //返回本周每天的故障总时间
    @MyLog(value = "获取本周每天的故障总时间")
    @RequestMapping("thisWeekTotal")
    public ReturnData thisWeekTotal(String deviceNum)
    {
        return faultEventService.thisWeekTotal(deviceNum);
    }
}
