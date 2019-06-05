package com.mj.scada.repository;

import com.mj.scada.bean.domain.MaintainPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-19 14:31
 */
@Repository
public interface MaintainPlanRepository extends JpaRepository<MaintainPlan,Integer>
{
}
