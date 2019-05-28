package com.mj.scada.repository;

import com.mj.scada.bean.domain.PermissionDo;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-05-13 10:35
 */
public interface PermissionRepository extends JpaRepository<PermissionDo,Integer>
{
}
