package com.mj.scada.repository;

import com.mj.scada.bean.domain.ledger.EquipLedger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-18 14:35
 */
@Repository
public interface EquipLedgerRepository extends JpaRepository<EquipLedger, Integer>
{
    EquipLedger findByEquipNum(String deviceNum);
    List<EquipLedger> findByStatus(String status);

    @Query(value = "select * from equip_ledger where status=?1 and propertyName like ?2",nativeQuery = true)
    List<EquipLedger> findByStatusAndPropertyName(String status,String name);
}
