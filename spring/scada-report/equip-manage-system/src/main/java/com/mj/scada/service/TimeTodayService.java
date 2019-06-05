package com.mj.scada.service;

import com.mj.scada.bean.domain.TimeToday;

import java.util.Map;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-04-02 18:10
 */
public interface TimeTodayService
{
    boolean isExist(String deviceNum);
    TimeToday findByDeviceNum(String deviceNum);
    void save(TimeToday today);
    Integer getTodayStartupTime(String deviceNum);
    Integer getTodayCloseTime(String deviceNum);
    Map<String,String> getAllTodayStartupTime();
}
