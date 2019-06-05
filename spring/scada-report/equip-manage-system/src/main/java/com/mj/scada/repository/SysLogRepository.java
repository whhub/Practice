package com.mj.scada.repository;

import com.mj.scada.bean.domain.SysLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-06-01 16:09
 */
@Repository
public interface SysLogRepository extends JpaRepository<SysLog,Long>
{
}
