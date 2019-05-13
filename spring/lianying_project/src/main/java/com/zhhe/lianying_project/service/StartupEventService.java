package com.zhhe.lianying_project.service;

import com.alibaba.fastjson.JSONObject;
import com.zhhe.lianying_project.bean.entity.StartupEvent;

import java.util.List;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-05 15:39
 */
public interface StartupEventService
{
    void save(StartupEvent startupEvent);
    List<String> findEvent(int num);
    List<JSONObject> findEventObj(int num);
    Long findAllNum();
}
