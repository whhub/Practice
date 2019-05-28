package com.mj.scada.service;

import com.mj.scada.bean.domain.EquipTimeHistory;
import com.mj.scada.repository.EquipTimeHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-04-02 18:11
 */
@Service
public class EquipTimeHistoryServiceImpl implements EquipTimeHistoryService
{
    @Autowired
    private EquipTimeHistoryRepository repository;

    @Override
    public EquipTimeHistory findByDeviceNumAndDate(String deviceNum, String date)
    {
        return repository.findByDeviceNumAndDate(deviceNum, date);
    }

    @Override
    public void save(EquipTimeHistory history)
    {
        repository.save(history);
    }

    //获取历史开机时间
    @Override
    public Integer getHisStartupTime(String deviceNum, String date)
    {
        EquipTimeHistory equipTimeHistory=repository.findByDeviceNumAndDate(deviceNum, date);
        return equipTimeHistory.getStartupTime();
    }
}
