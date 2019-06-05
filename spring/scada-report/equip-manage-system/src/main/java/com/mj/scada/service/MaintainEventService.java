package com.mj.scada.service;

import com.mj.scada.bean.domain.MaintainEvent;

import java.util.List;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-19 14:03
 */
public interface MaintainEventService
{
    String getAllMaintainInfo();
    List<MaintainEvent> findAll();
    String addEvent(String color,String msg);
}
