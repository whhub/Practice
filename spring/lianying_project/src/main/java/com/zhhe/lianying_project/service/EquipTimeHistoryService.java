package com.zhhe.lianying_project.service;

import com.zhhe.lianying_project.bean.entity.EquipTimeHistory;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-05 11:05
 */
public interface EquipTimeHistoryService
{
    EquipTimeHistory findByDeviceNumAndDate(String deviceNum,String date);
    void save(EquipTimeHistory equipTimeHistory);
}
