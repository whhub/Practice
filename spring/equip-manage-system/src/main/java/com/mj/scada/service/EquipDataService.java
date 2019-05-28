package com.mj.scada.service;

import com.mj.scada.bean.domain.algorithm.EquipData;
import com.mj.scada.bean.json.ReturnData;

import java.util.List;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-04-02 15:00
 */
public interface EquipDataService
{
    void saveData(String json);
    String saveInputData(String json);
    String findDataByType(String type);
    EquipData findById(Integer id);
    List<EquipData> findByKeyName(String keyName);
    List<EquipData> getSortEquip(String keyName,String type);
    EquipData findByTypeAndKeyName(String type,String keyName);
    EquipData findByDeviceNumAndTypeAndKeyName(String deviceNum,String type,String keyName);
    ReturnData linkData();
}
