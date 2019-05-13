package com.zhhe.lianying_project.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhhe.lianying_project.bean.entity.StartupEvent;
import com.zhhe.lianying_project.repository.StartupEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/*
 *Author:ZouHeng
 *Des:开机事件
 *Date:2019-03-05 15:39
 */
@Service
public class StartupEventServiceImpl implements StartupEventService
{
    @Autowired
    private StartupEventRepository repository;

    @Override
    @Transactional
    public void save(StartupEvent startupEvent)
    {
        repository.save(startupEvent);
    }

    @Override
    public List<String> findEvent(int num)
    {
        List<StartupEvent> eventList=repository.findEvent(num);
        System.out.println(eventList.size());
        List<String> list=new ArrayList<>();
        for (StartupEvent s:eventList)
        {
            list.add(s.getEvent());
        }
        return list;
    }

    @Override
    public List<JSONObject> findEventObj(int num)
    {
        List<String> list = findEvent(num);
        List<JSONObject> objList=new ArrayList<>();
        for (String s:list)
        {
            objList.add(JSON.parseObject(s));
        }
        return objList;
    }

    @Override
    public Long findAllNum()
    {
        return (long)repository.findAll().size();
    }
}
