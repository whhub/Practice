package com.zhhe.lianying_project.service;

import com.alibaba.fastjson.JSONObject;
import com.zhhe.lianying_project.bean.entity.ShutdownEvent;

import java.util.List;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-05 16:02
 */
public interface ShutdownEventService
{
    void save(ShutdownEvent shutdownEvent);
    List<String> findEvent(int num);
    List<JSONObject> findEventObj(int num);
    Long findAllNum();
}
