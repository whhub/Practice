package com.mj.scada.service;

import com.mj.scada.bean.domain.ledger.EquipLedger;
import com.mj.scada.bean.json.LedgerData;
import com.mj.scada.bean.json.ReturnData;
import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.util.List;
import java.util.Map;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-18 14:36
 */
public interface EquipLedgerService
{
    ReturnData updateEquipLedgerFromRest();
    Map<String, Integer> getEquipSort();
    Map<String, String> analysisStatus();
    ReturnData home();
    ReturnData add(String json);
    ReturnData delete(Integer id);
    ReturnData update(String json);
    ReturnData find(String status,String name);
    EquipLedger getLedger(String deviceNum);
    ReturnData findById(Integer id);
}
