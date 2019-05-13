package com.zhhe.lianying_project.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhhe.lianying_project.bean.entity.ShutdownEvent;
import com.zhhe.lianying_project.repository.ShutdownEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/*
 *Author:ZouHeng
 *Des:关机事件
 *Date:2019-03-05 16:03
 */
@Service
public class ShutdownEventServiceImpl implements ShutdownEventService
{
    @Autowired
    private ShutdownEventRepository repository;

    @Override
    @Transactional
    public void save(ShutdownEvent shutdownEvent)
    {
        repository.save(shutdownEvent);
    }

    @Override
    public List<String> findEvent(int num)
    {
        List<ShutdownEvent> eventList=repository.findEvent(num);
        List<String> list=new ArrayList<>();
        for (ShutdownEvent s:eventList)
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
