package com.zhhe.lianying_project.service;

import com.alibaba.fastjson.JSON;
import com.zhhe.lianying_project.bean.WebSocketMsg3;
import com.zhhe.lianying_project.bean.entity.StartupAndShutdown;
import com.zhhe.lianying_project.repository.StartupAndShutdownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *Author:ZouHeng
 *Des:整点开关机
 *Date:2019-03-12 14:20
 */
@Service
public class StartupAndShutdownServiceImpl implements StartupAndShutdownService
{
    @Autowired
    private StartupAndShutdownRepository repository;
    @Autowired
    private RestService restService;


    /**
     * 获取整点时间及该时间之前的所有数据，并将其整理为json格式
     * @param time
     * @return
     */
    @Override
    public String selectNum(Integer time)
    {
        List<StartupAndShutdown> list = repository.findAll();
        WebSocketMsg3 msg=new WebSocketMsg3();
        msg.setIndex("3");
        List<Map<String,String>> dataList=new ArrayList<>();
        for (int i=0;i<time+1;i++)
        {
            Map<String,String> map = new HashMap<>();
            map.put("time", list.get(i).getTime().toString());
            map.put("startupNum", list.get(i).getStartupNum().toString());
            map.put("shutdownNum", list.get(i).getShutdownNum().toString());
            dataList.add(map);
        }
        msg.setMsg(dataList);
        String json= JSON.toJSONString(msg);
        return json;
    }

    @Transactional
    @Override
    public void save(StartupAndShutdown startupAndShutdown)
    {
        repository.save(startupAndShutdown);
    }

    @Override
    public List<StartupAndShutdown> findAll()
    {
        return repository.findAll();
    }
}
