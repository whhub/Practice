package com.zhhe.lianying_project.service;

import com.zhhe.lianying_project.bean.entity.EquipTimeToday;

import java.util.List;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-04 15:36
 */
public interface EquipTimeService
{
    void save(EquipTimeToday equipTimeToday);
    List<EquipTimeToday> findAll();
    boolean isExist(String deviceNum);
    EquipTimeToday findByDeviceNum(String deviceNum);
}
