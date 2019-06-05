package com.mj.scada.service;

import com.alibaba.fastjson.JSON;
import com.mj.scada.bean.domain.MaintainEvent;
import com.mj.scada.repository.MaintainEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-19 14:04
 */
@Service
public class MaintainEventServiceImpl implements MaintainEventService
{
    @Autowired
    private MaintainEventRepository maintainEventRepository;

    //返回所有维护事件的json
    @Override
    public String getAllMaintainInfo()
    {
        List<MaintainEvent> list = maintainEventRepository.findAll();
        return JSON.toJSONString(list);
    }

    @Override
    public List<MaintainEvent> findAll()
    {
        return maintainEventRepository.findAll();
    }

    @Override
    public String addEvent(String color, String msg)
    {
        MaintainEvent event=new MaintainEvent();
        event.setColor(color);
        event.setMsg(msg);
        Integer id=findAll().size()+1;
        event.setId(id);
        maintainEventRepository.save(event);
        return "succ";
    }
}
