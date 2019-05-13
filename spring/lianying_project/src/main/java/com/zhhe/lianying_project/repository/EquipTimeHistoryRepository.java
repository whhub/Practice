package com.zhhe.lianying_project.repository;

import com.zhhe.lianying_project.bean.entity.EquipTimeHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-05 11:21
 */
public interface EquipTimeHistoryRepository extends JpaRepository<EquipTimeHistory,Long>
{
    EquipTimeHistory findByDeviceNumAndDate(String deviceNum,String date);
}
