package com.mj.scada.repository;

import com.mj.scada.bean.domain.ledger.EquipTotal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-20 9:43
 */
@Repository
public interface EquipTotalRepository extends JpaRepository<EquipTotal,Integer>
{
    EquipTotal findByYear(Integer year);
}
