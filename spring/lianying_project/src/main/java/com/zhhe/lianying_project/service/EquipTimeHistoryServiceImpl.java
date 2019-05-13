package com.zhhe.lianying_project.service;

import com.zhhe.lianying_project.bean.entity.EquipTimeHistory;
import com.zhhe.lianying_project.repository.EquipTimeHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-05 11:29
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
    @Transactional
    public void save(EquipTimeHistory equipTimeHistory)
    {
        repository.save(equipTimeHistory);
    }
}
