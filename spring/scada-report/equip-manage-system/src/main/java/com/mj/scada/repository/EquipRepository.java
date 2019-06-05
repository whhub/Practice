package com.mj.scada.repository;

import com.mj.scada.bean.domain.equipMap.Equip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-05-28 15:02
 */
@Repository
public interface EquipRepository extends JpaRepository<Equip,Integer>
{
}
