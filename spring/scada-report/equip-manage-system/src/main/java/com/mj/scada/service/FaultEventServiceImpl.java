package com.mj.scada.service;

import com.mj.scada.bean.domain.FaultEvent;
import com.mj.scada.bean.json.ReturnData;
import com.mj.scada.repository.FaultEventRepository;
import com.mj.scada.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-05-29 16:04
 */
@Service
public class FaultEventServiceImpl implements FaultEventService
{
    @Autowired
    private FaultEventRepository faultEventRepository;

    //开始故障
    @Override
    @Transactional
    public void eventStart(String deviceNum,String faultCode)
    {
        FaultEvent faultEvent=new FaultEvent();
        faultEvent.setDeviceNum(deviceNum);
        faultEvent.setType(faultCode);
        faultEvent.setTodayDate(Util.getCurrentDateOrTime("date"));
        faultEvent.setStartDate(Util.getCurrentDateAndTime());
        faultEventRepository.save(faultEvent);
    }

    //故障结束
    @Override
    public void eventEnd(String deviceNum)
    {
        FaultEvent faultEvent = faultEventRepository.findEventEnd(deviceNum);
        faultEvent.setEndDate(Util.getCurrentDateAndTime());
        Integer total = Util.calculateTime(faultEvent.getStartDate(), faultEvent.getEndDate());
        faultEvent.setTotalTime(total);
        faultEventRepository.save(faultEvent);
    }

    @Override
    public List<FaultEvent> getTodayList(String deviceNum, String todayDate)
    {
        return faultEventRepository.findByDeviceNumAndTodayDate(deviceNum, todayDate);
    }

    //得到故障中的那条数据
    @Override
    public FaultEvent getFault(String deviceNum)
    {
        List<FaultEvent> list = faultEventRepository.findByDeviceNumAndTodayDate(deviceNum, Util.getCurrentDateOrTime("date"));
        for (FaultEvent faultEvent:list)
        {
            if (faultEvent.getEndDate()==null)
            {
                return faultEvent;
            }
        }
        return null;
    }

    //返回时间区间的每天的事件列表
    @Override
    public ReturnData sectionList(String deviceNum,String startDate, String endDate) throws ParseException
    {
        List<String> dateList = Util.getDateSection(startDate, endDate);
        List<FaultEvent> list = new ArrayList<>();
        for (String s : dateList)
        {
            List<FaultEvent> faultEvents = faultEventRepository.findByDeviceNumAndTodayDate(deviceNum, s);
            for (FaultEvent event : faultEvents)
            {
                list.add(event);
            }
        }
        return new ReturnData(0,"操作成功",list);
    }

    //返回当天的事件列表
    @Override
    public ReturnData todayList(String deviceNum)
    {
        List<FaultEvent> faultEvents = faultEventRepository.findByDeviceNumAndTodayDate(deviceNum, Util.getCurrentDateOrTime("date"));
        return new ReturnData(0,"操作成功",faultEvents);
    }

    /**
     * 获取这周和上周的每天故障总时间
     *
     * @param index 0：获取本周每天故障总时间       1：获取上周每天故障总时间
     * @return 按顺序依次是周一到周日
     */
    @Override
    public ReturnData weekTotal(String deviceNum,Integer index)
    {
        List<Integer> list = new ArrayList<>();
        List<String> weekDates = null;
        if (index == 0)         //本周
        {
            Integer todayIndex = -1;
            weekDates = Util.getThisWeekDates();
            String today = LocalDate.now().toString();
            for (int i = 0; i < weekDates.size(); i++)
            {
                if (weekDates.get(i).equals(today))
                {
                    todayIndex = i;
                    break;
                }
            }
            for (int i = 0; i < weekDates.size(); i++)
            {
                Integer total = 0;
                if (i < todayIndex)
                {
                    List<FaultEvent> faultEvents = faultEventRepository.findByDeviceNumAndTodayDate(deviceNum,weekDates.get(i));
                    for (FaultEvent event : faultEvents)
                    {
                        total += event.getTotalTime();
                    }
                    list.add(total);
                }
                else
                {
                    list.add(0);
                }
            }
        }
        else if (index == 1)    //上周
        {
            weekDates = Util.getLastWeekDates();
            for (int i = 0; i < weekDates.size(); i++)
            {
                List<FaultEvent> faultEvents = faultEventRepository.findByDeviceNumAndTodayDate(deviceNum,weekDates.get(i));
                Integer total = 0;
                for (FaultEvent event : faultEvents)
                {
                    total += event.getTotalTime();
                }
                list.add(total);
            }
        }

        return new ReturnData(0,"操作成功",list);
    }

    //返回本周周一到今天故障时间统计
    @Override
    public ReturnData thisWeekTotal(String deviceNum)
    {
        List<String> list = new ArrayList<>();
        List<String> weekDates = Util.getThisWeekDates();
        String today = LocalDate.now().toString();
        Integer todayIndex = 0;
        for (int i = 0; i < weekDates.size(); i++)
        {
            if (weekDates.get(i).equals(today))
            {
                todayIndex = i;
                break;
            }
        }
        for (int i = 0; i < weekDates.size(); i++)
        {
            Integer total = 0;
            if (i < todayIndex)
            {
                List<FaultEvent> faultEvents = faultEventRepository.findByDeviceNumAndTodayDate(deviceNum,weekDates.get(i));
                for (FaultEvent event : faultEvents)
                {
                    total += event.getTotalTime();
                }
                list.add(total.toString()+"h");
            }
            else if (i==todayIndex)
            {
                list.add("计算中");
                break;
            }
        }
        return new ReturnData(0,"操作成功",list);
    }


}
