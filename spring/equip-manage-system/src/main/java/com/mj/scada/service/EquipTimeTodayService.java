package com.mj.scada.service;

import com.mj.scada.bean.domain.EquipTimeToday;

import java.util.Map;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-04-02 18:10
 */
public interface EquipTimeTodayService
{
    boolean isExist(String deviceNum);
    EquipTimeToday findByDeviceNum(String deviceNum);
    void save(EquipTimeToday today);
    Integer getTodayStartupTime(String deviceNum);
    Integer getTodayCloseTime(String deviceNum);
    Map<String,String> getAllTodayStartupTime();
}
