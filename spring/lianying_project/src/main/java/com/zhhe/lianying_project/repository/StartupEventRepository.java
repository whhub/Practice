package com.zhhe.lianying_project.repository;

import com.zhhe.lianying_project.bean.entity.StartupEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-05 15:38
 */
@Repository
public interface StartupEventRepository extends JpaRepository<StartupEvent,Long>
{
    @Query(value = "select * from sdd_startup_event order by id desc limit ?1",nativeQuery = true)
    List<StartupEvent> findEvent(int num);
}
