package com.mj.scada.service;

import com.mj.scada.bean.domain.TimeHistory;
import com.mj.scada.repository.TimeHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-04-02 18:11
 */
@Service
public class TimeHistoryServiceImpl implements TimeHistoryService
{
    @Autowired
    private TimeHistoryRepository repository;

    @Override
    public TimeHistory findByDeviceNumAndDate(String deviceNum, String date)
    {
        return repository.findByDeviceNumAndDate(deviceNum, date);
    }

    @Override
    public void save(TimeHistory history)
    {
        repository.save(history);
    }

    //获取历史开机时间
    @Override
    public Integer getHisStartupTime(String deviceNum, String date)
    {
        TimeHistory timeHistory =repository.findByDeviceNumAndDate(deviceNum, date);
        return timeHistory.getOpenTime();
    }
}
