package com.mj.scada.repository;

import com.mj.scada.bean.domain.type.EquipType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-27 10:54
 */
@Repository
public interface EquipTypeRepository extends JpaRepository<EquipType,Integer>
{
    @Query(value = "select * from equip_type where typeName like ?1",nativeQuery = true)
    List<EquipType> fuzzyByName(String name);

    List<EquipType> findByStatus(String status);
    List<EquipType> findByTypeNameAndStatus(String typeName,String status);
}
