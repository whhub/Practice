package com.zhhe.lianying_project.service;

import com.zhhe.lianying_project.bean.entity.EquipTimeToday;
import com.zhhe.lianying_project.repository.EquipTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-04 15:36
 */
@Service
public class EquipTimeServiceImpl implements EquipTimeService
{
    @Autowired
    private EquipTimeRepository repository;

    @Override
    @Transactional
    public void save(EquipTimeToday equipTimeToday)
    {
        repository.save(equipTimeToday);
    }

    @Override
    public List<EquipTimeToday> findAll()
    {
        return repository.findAll();
    }

    //判断是否存在记录
    @Override
    public boolean isExist(String deviceNum)
    {
        EquipTimeToday equipTimeToday =repository.findByDeviceNum(deviceNum);
        if (equipTimeToday ==null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    public EquipTimeToday findByDeviceNum(String deviceNum)
    {
        return repository.findByDeviceNum(deviceNum);
    }

}
