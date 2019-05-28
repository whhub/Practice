package com.mj.scada.repository;

import com.mj.scada.bean.domain.EquipTimeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-04-02 18:08
 */
@Repository
public interface EquipTimeHistoryRepository extends JpaRepository<EquipTimeHistory,Integer>
{
    EquipTimeHistory findByDeviceNumAndDate(String deviceNum,String date);
}
