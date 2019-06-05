package com.mj.scada.service;

import com.mj.scada.bean.domain.type.EquipType;
import com.mj.scada.bean.json.ReturnData;
import com.mj.scada.bean.json.TypeData;

import java.util.List;
import java.util.Map;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-27 10:55
 */
public interface EquipTypeService
{
    ReturnData home();
    ReturnData addType(String json);
    ReturnData delType(Integer id);
    ReturnData updateType(String json);
    ReturnData findAllTypeName();
    ReturnData fuzzyTypeName(String name);
    ReturnData find(String typeName,String status);
    ReturnData findAll();
    ReturnData tree();
}
