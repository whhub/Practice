package com.mj.scada.service;

import com.mj.scada.bean.domain.EquipTimeHistory;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-04-02 18:10
 */
public interface EquipTimeHistoryService
{
    EquipTimeHistory findByDeviceNumAndDate(String deviceNum, String date);
    void save(EquipTimeHistory history);
    Integer getHisStartupTime(String deviceNum, String date);
}
