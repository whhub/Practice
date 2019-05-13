package com.zhhe.lianying_project.service;

import com.alibaba.fastjson.JSONObject;
import com.zhhe.lianying_project.bean.RestEquipList;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-01 17:45
 */
public interface RestService
{
//    RestEquipList getEquipList();
    Integer getTotalEquipNum();
    Integer getStartupEquipNum();
    Integer getShutdownEquipNum();
    String getStartupEquipRatio();
    Integer getTodayStartupTime(String deviceNum);
    Integer getHisStartupTime(String deviceNum,String date);
    String getEquipSort();
}
