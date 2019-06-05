package com.mj.scada.repository;

import com.mj.scada.bean.domain.FaultEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-05-29 16:02
 */
@Repository
public interface FaultEventRepository extends JpaRepository<FaultEvent,Integer>
{
    @Query(value = "select * from fault_event where deviceNum=?1 and endDate=NULL ",nativeQuery = true)
    FaultEvent findEventEnd(String deviceNum);

    List<FaultEvent> findByDeviceNumAndTodayDate(String deviceNum,String todayDate);
}
