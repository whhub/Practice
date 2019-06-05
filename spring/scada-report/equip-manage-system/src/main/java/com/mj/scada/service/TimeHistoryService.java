package com.mj.scada.service;

import com.mj.scada.bean.domain.TimeHistory;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-04-02 18:10
 */
public interface TimeHistoryService
{
    TimeHistory findByDeviceNumAndDate(String deviceNum, String date);
    void save(TimeHistory history);
    Integer getHisStartupTime(String deviceNum, String date);
}
