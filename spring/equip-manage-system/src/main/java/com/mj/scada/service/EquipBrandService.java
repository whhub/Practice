package com.mj.scada.service;

import com.mj.scada.bean.domain.type.EquipBrand;
import com.mj.scada.bean.domain.type.EquipType;
import com.mj.scada.bean.json.ReturnData;
import org.hibernate.loader.custom.Return;

import java.util.List;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-27 10:56
 */
public interface EquipBrandService
{
    List<EquipBrand> findAll();
    ReturnData updateBrand(String json);
    ReturnData addBrand(String json);
    ReturnData delBrand(Integer id);
    void deleteAll(Integer typeId);
    List<EquipBrand> find(List<EquipType> types);
    ReturnData findByTypeId(Integer typeId);
}
