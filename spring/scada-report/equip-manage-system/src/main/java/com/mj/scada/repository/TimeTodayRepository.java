package com.mj.scada.repository;

import com.mj.scada.bean.domain.TimeToday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-04-02 18:08
 */
@Repository
public interface TimeTodayRepository extends JpaRepository<TimeToday,Integer>
{
    TimeToday findByDeviceNum(String deviceNum);
}
