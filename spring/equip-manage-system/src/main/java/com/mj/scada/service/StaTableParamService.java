package com.mj.scada.service;

import com.mj.scada.bean.domain.statistics.StatisticTableParam;
import com.mj.scada.bean.json.SeeTableData;

import javax.script.ScriptException;
import java.util.List;
import java.util.Map;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-04-10 14:42
 */
public interface StaTableParamService
{
    List<StatisticTableParam> findAll();
    void addTableParam(String tableName,Map<String,String> map);
    void addTableParam(String tableName,StatisticTableParam param);
    void delTableParams(String tableName);
    void deleteById(Integer id);
    Map<String, SeeTableData> seeTable(String tableName) throws ScriptException;
    StatisticTableParam findById(Integer id);
    void save(StatisticTableParam param);
    List<StatisticTableParam> getTableParams(String tableName);
}
