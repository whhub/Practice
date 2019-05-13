package com.zhhe.lianying_project.repository;

import com.zhhe.lianying_project.bean.entity.EquipTimeToday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-04 15:34
 */
@Repository
public interface EquipTimeRepository extends JpaRepository<EquipTimeToday,String>
{
    List<EquipTimeToday> findAll();
    EquipTimeToday findByDeviceNum(String deviceNum);
}
