package com.mj.scada.repository;

import com.mj.scada.bean.domain.FaultCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-05-29 13:48
 */
@Repository
public interface FaultCodeRepository extends JpaRepository<FaultCode,Integer>
{
}
