package com.mj.scada.service;

import java.util.Map;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-20 9:44
 */
public interface EquipTotalService
{
    Map<Integer,Integer> getLatestEquipTotal();
    void saveThisYearData();
}
