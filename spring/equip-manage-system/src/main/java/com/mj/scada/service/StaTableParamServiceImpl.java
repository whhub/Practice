package com.mj.scada.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mj.scada.bean.domain.algorithm.AlgorithmModel;
import com.mj.scada.bean.json.SeeTableData;
import com.mj.scada.util.Util;
import com.mj.scada.bean.domain.algorithm.EquipData;
import com.mj.scada.bean.domain.statistics.StatisticTableParam;
import com.mj.scada.repository.StatisticTableParamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-04-10 14:42
 */
@Service
public class StaTableParamServiceImpl implements StaTableParamService
{
    @Autowired
    private StatisticTableParamRepository statisticTableParamRepository;
    @Autowired
    private AlgorithmModelService algorithmModelService;
    @Autowired
    private EquipDataService equipDataService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private EquipTimeTodayService equipTimeTodayService;

    @Override
    public List<StatisticTableParam> findAll()
    {
        return statisticTableParamRepository.findAll();
    }

    //添加统计表中的元素
    @Override
    @Transactional
    public void addTableParam(String tableName,Map<String, String> map)
    {
        StatisticTableParam tableParam = new StatisticTableParam();
        tableParam.setValue(tableName, map.get("paramName"), Boolean.parseBoolean(map.get("isUse")), map.get("keyName"), map.get("paramType"), map.get("connectType"), map.get("connectName"), map.get("fixedValue"));
        statisticTableParamRepository.save(tableParam);
    }

    @Override
    @Transactional
    public void addTableParam(String tableName, StatisticTableParam param)
    {
        StatisticTableParam tableParam = new StatisticTableParam();
        tableParam.setValue(tableName, param.getParamName(), param.getIsUse(), param.getKeyName(), param.getParamType(), param.getConnectType(), param.getConnectName(), param.getFixedValue());
        statisticTableParamRepository.save(tableParam);
    }

    //删除表的所有元素
    @Override
    @Transactional
    public void delTableParams(String tableName)
    {
        List<StatisticTableParam> list = statisticTableParamRepository.findByTableName(tableName);
        for (StatisticTableParam t : list)
        {
            statisticTableParamRepository.delete(t);
        }
    }

    @Override
    @Transactional
    public void deleteById(Integer id)
    {
        statisticTableParamRepository.delete(id);
    }

    //查看表，并显示数据
    @Override
    public Map<String, SeeTableData> seeTable(String tableName) throws ScriptException
    {
        List<StatisticTableParam> paramList = statisticTableParamRepository.findByTableName(tableName);
        Boolean isAll = true; //是否展示所有设备的数据
        String sortType = "";   //关联的设备数据类型
        for (StatisticTableParam t : paramList)
        {
            if (!t.getConnectType().equals("算法模型") && !t.getConnectType().equals("通用类数据") && !t.getConnectType().equals("导入类数据"))
            {
                isAll = false;
                sortType = t.getConnectType();
                break;
            }
        }
        Map<String, SeeTableData> returnMap=new HashMap<>();
        for (StatisticTableParam t : paramList)
        {
            if (isAll)  //显示所有设备的数据
            {
                if (t.getConnectType().equals("算法模型"))
                {
                    AlgorithmModel model = algorithmModelService.findByName(t.getConnectName());
                    Map<String, String> map = algorithmModelService.calculateAll(model.getModelName());
                    SeeTableData seeTableData=new SeeTableData(t.getParamType(),map);
                    returnMap.put(t.getParamName(), seeTableData);
                }
                else if (t.getConnectType().equals("通用类数据"))
                {
                    //type只可能是"通用类数据"或"导入类数据"，而不可能是"设备类数据"
                    List<EquipData> equipDataList = equipDataService.findByKeyName(t.getConnectName());

                    if (t.getConnectName().equals("startupTime"))
                    {
                        //获取所有设备的开机时间
                        Map<String, String> timeMap = equipTimeTodayService.getAllTodayStartupTime();
                        SeeTableData seeTableData=new SeeTableData(t.getParamType(),timeMap);
                        returnMap.put(t.getParamName(), seeTableData);
                    }
                    else
                    {
                        Map<String, String> map = new HashMap<>();
                        for (EquipData d : equipDataList)
                        {
                            map.put(d.getDeviceNum(), d.getValue());
                        }
                        SeeTableData seeTableData=new SeeTableData(t.getParamType(),map);
                        returnMap.put(t.getParamName(), seeTableData);
                    }
                }
                else if (t.getConnectType().equals("导入类数据"))
                {
                    List<EquipData> equipDataList = equipDataService.findByKeyName(t.getConnectName());
                    EquipData equipData = equipDataList.get(0);

                    Map<String, String> map = new HashMap<>();
                    for (Map.Entry<String, String> entry : Util.equipNameMap.entrySet())
                    {
                        map.put(entry.getKey(), equipData.getValue());
                    }
                    SeeTableData seeTableData=new SeeTableData(t.getParamType(),map);
                    returnMap.put(t.getParamName(), seeTableData);
                }
            }
            else        //显示单类设备的数据
            {
                if (t.getConnectType().equals("算法模型"))
                {
                    AlgorithmModel model = algorithmModelService.findByName(t.getConnectName());
                    Map<String, String> map = algorithmModelService.calculateSort(model.getModelName(), sortType);
                    SeeTableData seeTableData=new SeeTableData(t.getParamType(),map);
                    returnMap.put(t.getParamName(), seeTableData);
                }
                else if (t.getConnectType().equals("通用类数据"))
                {
                    List<EquipData> equipDataList = equipDataService.getSortEquip(t.getConnectName(), sortType);
                    if (t.getConnectName().equals("startupTime"))
                    {
                        //获取设备的开机时间
                        Map<String, String> timeMap = new HashMap<>();
                        for (EquipData d : equipDataList)
                        {
                            String time = equipTimeTodayService.getTodayStartupTime(d.getDeviceNum()).toString();
                            timeMap.put(d.getDeviceNum(), time);
                        }
                        SeeTableData seeTableData=new SeeTableData(t.getParamType(),timeMap);
                        returnMap.put(t.getParamName(), seeTableData);
                    }
                    else
                    {
                        Map<String, String> map = new HashMap<>();
                        for (EquipData d : equipDataList)
                        {
                            map.put(d.getDeviceNum(), d.getValue());
                        }
                        SeeTableData seeTableData=new SeeTableData(t.getParamType(),map);
                        returnMap.put(t.getParamName(), seeTableData);
                    }
                }
                else if (t.getConnectType().equals("导入类数据"))
                {
                    List<EquipData> equipDataList = equipDataService.getSortEquip(t.getConnectName(), sortType);
                    List<EquipData> dataList = equipDataService.findByKeyName(t.getConnectName());
                    EquipData data = dataList.get(0);
                    Map<String, String> map = new HashMap<>();
                    for (EquipData d : equipDataList)
                    {
                        map.put(d.getDeviceNum(), data.getValue());
                    }
                    SeeTableData seeTableData=new SeeTableData(t.getParamType(),map);
                    returnMap.put(t.getParamName(), seeTableData);
                }
                else    //设备类数据
                {
                    List<EquipData> equipDataList = equipDataService.getSortEquip(t.getConnectName(), sortType);
                    Map<String, String> map = new HashMap<>();
                    for (EquipData d : equipDataList)
                    {
                        map.put(d.getDeviceNum(), d.getValue());
                    }
                    SeeTableData seeTableData=new SeeTableData(t.getParamType(),map);
                    returnMap.put(t.getParamName(), seeTableData);
                }
            }
        }
        return returnMap;
    }

    @Override
    public StatisticTableParam findById(Integer id)
    {
        return statisticTableParamRepository.findOne(id);
    }

    @Override
    @Transactional
    public void save(StatisticTableParam param)
    {
        statisticTableParamRepository.save(param);
    }

    @Override
    public List<StatisticTableParam> getTableParams(String tableName)
    {
        return statisticTableParamRepository.findByTableName(tableName);
    }

}
