package com.zhhe.lianying_project.repository;

import com.zhhe.lianying_project.bean.entity.ShutdownEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-05 16:01
 */
@Repository
public interface ShutdownEventRepository extends JpaRepository<ShutdownEvent,Long>
{
    @Query(value = "select * from sdd_shutdown_event order by id desc limit ?1",nativeQuery = true)
    List<ShutdownEvent> findEvent(int num);
}
