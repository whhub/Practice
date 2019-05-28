package com.mj.scada.repository;

import com.mj.scada.bean.domain.type.EquipBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-27 10:55
 */
@Repository
public interface EquipBrandRepository extends JpaRepository<EquipBrand,Integer>
{
    List<EquipBrand> findByTypeId(Integer typeId);
}
