package com.mj.scada.service;

import com.mj.scada.bean.Signal;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-05-28 15:03
 */
public interface EquipService
{
    void updateMap();
    Map<String,String> getEquipNameMap();
    Map<String,String> getEquipSortMap();
    List<String> getEquipSortList(String type);
    Map<String,Integer> getEquipSort();
    Map<String,Integer> getEquipsStartup();
    Map<String,Integer> getEquipsFault();
    Map<String, Map<String, String>> getEquipList();
    List<String> getDeviceNumList();
    Integer judgeEquipStateByMsg(Signal signal);
}
