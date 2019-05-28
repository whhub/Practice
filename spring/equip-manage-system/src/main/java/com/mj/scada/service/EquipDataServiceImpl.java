package com.mj.scada.service;

import com.alibaba.fastjson.JSON;
import com.mj.scada.bean.json.ReturnData;
import com.mj.scada.repository.EquipDataRepository;
import com.mj.scada.bean.Signal;
import com.mj.scada.bean.domain.algorithm.EquipData;
import com.mj.scada.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *Author:ZouHeng
 *Des:设备数据分为："源数据状态类"、"实际统计数据"
 *Date:2019-04-02 15:01
 */
@Service
public class EquipDataServiceImpl implements EquipDataService
{
    @Autowired
    private EquipDataRepository equipDataRepository;
    @Autowired
    private EquipTimeTodayService equipTimeTodayService;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${my-data.type1}")
    private String type1;
    @Value("${my-data.type2}")
    private String type2;
    @Value("${my-data.type3}")
    private String type3;

    /**
     * 存储RabbitMQ数据，在接收到RabbitMQ信息的时候调用，解析RabbitMQ信息
     * @param json
     */
    @Override
    @Transactional
    public void saveData(String json)
    {
        Signal signal = JSON.parseObject(json, Signal.class);
        //存储设备底层源数据
        Map<String, String> stateMap = signal.getData().getState();     //状态类
        Map<String, String> customMap = signal.getData().getCustom();   //自定义类
        transferAndSaveData(stateMap, signal,type1);
        transferAndSaveData(customMap, signal, null);
        //存储开机时间字段，值为空，用到的时候实时计算
        EquipData startupTimeData=new EquipData();
        startupTimeData.setType(type1);
        startupTimeData.setKeyName("startupTime");
        EquipData data=equipDataRepository.findByTypeAndKeyName(type1, "startupTime");
        Integer id=getId(data);
        startupTimeData.setId(id);
        equipDataRepository.save(startupTimeData);
    }

    /**
     * 存储用户导入的数据
     * @param json：{"keyValue1":"value1","keyValue2":"value2"}
     * @return
     */
    @Override
    @Transactional
    public String saveInputData(String json)
    {
        //存储用户输入的数据
        Map<String,String> map = JSON.parseObject(json, Map.class);
        for (Map.Entry<String,String> entry:map.entrySet())
        {
            EquipData equipData=equipDataRepository.findByTypeAndKeyName(type2, entry.getKey());
            Integer id=getId(equipData);
            EquipData data=new EquipData(type2,entry.getKey(),entry.getValue());
            data.setId(id);
            equipDataRepository.save(data);
        }
        return "succ";
    }

    //根据数据类型查找数据
    @Override
    public String findDataByType(String type)
    {
        List<EquipData> list=equipDataRepository.findByType(type);
        List<String> strList=new ArrayList<>();
        if(type==type1) //通用类数据
        {
            for (int i=0;i<list.size();i++)
            {
                if (!strList.contains(list.get(i).getKeyName()))
                {
                    strList.add(list.get(i).getKeyName());
                }
            }
            //添加开机时间
            strList.add("startupTime");
        }
        else
        {
            for (EquipData data:list)
            {
                strList.add(data.getKeyName());
            }
        }
        return JSON.toJSONString(strList);
    }

    @Override
    public EquipData findById(Integer id)
    {
        return equipDataRepository.findOne(id);
    }

    @Override
    public List<EquipData> findByKeyName(String keyName)
    {
        return equipDataRepository.findByKeyName(keyName);
    }

    //获取某个种类,某个KeyName的所有设备
    @Override
    public List<EquipData> getSortEquip(String keyName,String type)
    {
        List<EquipData> dataList = findByKeyName(keyName);
        List<EquipData> equipDataList = new ArrayList<>();
        for (EquipData d : dataList)
        {
            if (Util.equipSortMap.get(d.getDeviceNum()).equals(type))
            {
                equipDataList.add(d);
            }
        }
        return equipDataList;
    }

    @Override
    public EquipData findByTypeAndKeyName(String type, String keyName)
    {
        return equipDataRepository.findByTypeAndKeyName(type, keyName);
    }

    @Override
    public EquipData findByDeviceNumAndTypeAndKeyName(String deviceNum, String type, String keyName)
    {
        return equipDataRepository.findByDeviceNumAndTypeAndKeyName(deviceNum, type, keyName);
    }


    //关联数据分类(AlgorithmController中调用)
    @Override
    public ReturnData linkData()
    {
        Map<String,List<String>> map = new HashMap<>();
        List<String> typeList=new ArrayList<>();
        typeList.add(type1);
        typeList.add(type2);
        typeList.add(type3);
        for (String s:typeList)
        {
            List<EquipData> dataList = equipDataRepository.findByType(s);
            List<String> list = new ArrayList<>();
            for (EquipData d:dataList)
            {
                list.add(d.getKeyName());
            }
            map.put(s, list);
        }
        return new ReturnData(0,"操作成功",map);
    }


    /**
     * 解析并存储数据(RabbitMQ数据，类型为：设备类型)
     * @param type type为空时：根据设备类型存储，
     *             type不为空时：根据type的值存储
     */
    private void transferAndSaveData(Map<String,String> map, Signal signal,String type)
    {
        if (map != null && map.size() != 0)
        {
            if (type==null)
            {
                //根据设备类型存储
                type= Util.equipSortMap.get(signal.getDeviceNum());
            }
            for (Map.Entry<String, String> entry : map.entrySet())
            {
                EquipData equipData = equipDataRepository.findByDeviceNumAndTypeAndKeyName(signal.getDeviceNum(), type, entry.getKey());
                Integer id = 0;
                if (equipData == null)
                {
                    id = equipDataRepository.findSize() + 1;
                }
                else
                {
                    id = equipData.getId();
                }

                EquipData data = new EquipData();
                data.setId(id);
                data.setDeviceNum(signal.getDeviceNum());
                data.setType(type);
                data.setKeyName(entry.getKey());
                data.setValue(entry.getValue());
                data.setReserve(null);
                equipDataRepository.save(data);
            }
        }
    }

    private Integer getId(EquipData equipData)
    {
        Integer id = 0;
        if (equipData == null)
        {
            id = equipDataRepository.findSize() + 1;
        }
        else
        {
            id = equipData.getId();
        }
        return id;
    }


}
