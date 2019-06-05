package com.mj.scada.repository;

import com.mj.scada.bean.domain.MaintainEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-19 11:45
 */
@Repository
public interface MaintainEventRepository extends JpaRepository<MaintainEvent,Integer>
{
}
