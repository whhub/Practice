package com.mj.scada.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mj.scada.bean.EquipSort;
import com.mj.scada.bean.RestEquipList;
import com.mj.scada.bean.RestEquipListData;
import com.mj.scada.bean.Signal;
import com.mj.scada.bean.domain.equipMap.Equip;
import com.mj.scada.bean.json.ReturnData;
import com.mj.scada.config.ConfigBean;
import com.mj.scada.rabbitmq.Consumer;
import com.mj.scada.repository.EquipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-05-28 15:04
 */
@Service
public class EquipServiceImpl implements EquipService
{
    @Autowired
    private EquipRepository equipRepository;
    @Autowired
    private RestTemplate restTemplate;

    //同步设备时更新数据库
    @Override
    @Transactional
    public void updateMap()
    {
        equipRepository.deleteAll();
        Map<String,String> equipNameMap=getEquipNameMapByRest();
        Map<String,String> equipSortMap=getEquipSortMapByRest();
        List<String> deviceNumList=getDeviceNumListByRest();
        for (String s:deviceNumList)
        {
            Equip equip=new Equip(s,equipNameMap.get(s),equipSortMap.get(s));
            equipRepository.save(equip);
        }
    }

    //返回Map<deviceNum,equipName>
    @Override
    public Map<String, String> getEquipNameMap()
    {
        List<Equip> list=equipRepository.findAll();
        Map<String,String> map = new HashMap<>();
        for (Equip equip:list)
        {
            map.put(equip.getDeviceNum(), equip.getEquipName());
        }
        return map;
    }

    //返回Map<deviceNum,equipSort>
    @Override
    public Map<String, String> getEquipSortMap()
    {
        List<Equip> list=equipRepository.findAll();
        Map<String,String> map = new HashMap<>();
        for (Equip equip:list)
        {
            map.put(equip.getDeviceNum(), equip.getEquipSort());
        }
        return map;
    }

    /**
     * 获取某类型的所有设备
     * @param type  设备类型
     * @return  list中的元素是设备的deviceNum
     */
    @Override
    public List<String> getEquipSortList(String type)
    {
        List<String> list = new ArrayList<>();
        for (Map.Entry<String,String> entry:getEquipSortMap().entrySet())
        {
            if (entry.getValue().equals(type))
            {
                list.add(entry.getKey());
            }
        }
        return list;
    }

    /**
     * 得到设备的类别和数量
     * @return map中的键值对："注塑机":3
     */
    @Override
    public Map<String, Integer> getEquipSort()
    {
        Map<String,Integer> map = new HashMap<>();
        for (Map.Entry<String,String> entry:getEquipSortMap().entrySet())
        {
            if (map.containsKey(entry.getValue()))
            {
                //已包含该类，数量加1
                Integer num = map.get(entry.getValue()) + 1;
                map.put(entry.getValue(), num);
            }
            else
            {
                //不包含该类别
                map.put(entry.getValue(), 1);
            }
        }
        return map;
    }

    //得到所有设备的开关机状态(0：关机  1：开机）
    @Override
    public Map<String, Integer> getEquipsStartup()
    {
        Map<String,Integer> map=new HashMap<>();

        //遍历是否所有设备都接收到了消息
        for (Map.Entry<String,String> entry:getEquipNameMap().entrySet())
        {
            //未接受到此设备消息，表示该设备关机
            if (!Consumer.signalMap.contains(entry.getKey()))
            {
                map.put(entry.getKey(), 1);
            }
        }
        //遍历从RabbitMQ接收到的消息
        for (Map.Entry<String, JSONObject> entry : Consumer.signalMap.entrySet())
        {
            Signal signal = JSON.parseObject(entry.getValue().toJSONString(), Signal.class);
            map.put(entry.getKey(), judgeEquipStateByMsg(signal));
        }
        return map;
    }

    //得到所有设备故障状态
    @Override
    public Map<String, Integer> getEquipsFault()
    {
        Map<String,Integer> map=new HashMap<>();
        //遍历是否所有设备都接收到了消息
        for (Map.Entry<String,String> entry:getEquipNameMap().entrySet())
        {
            //未接受到此设备消息，表示该设备关机，则设备无故障
            if (!Consumer.signalMap.contains(entry.getKey()))
            {
                map.put(entry.getKey(), 0);
            }
        }
        //遍历从RabbitMQ接收到的消息
        for (Map.Entry<String, JSONObject> entry : Consumer.signalMap.entrySet())
        {
            Signal signal = JSON.parseObject(entry.getValue().toJSONString(), Signal.class);
            //根据信号判断设备是否故障
            Integer fault = 0;
            if (signal.getData().getState() == null)
            {
                fault = 0;
            }
            else
            {
                if (!signal.getData().getState().containsKey("fault"))
                {
                    fault = 0;
                }
                else
                {
                    if (signal.getData().getState().get("fault").equals("1"))
                    {
                        fault = 1;
                    }
                    else
                    {
                        fault = 0;
                    }
                }
            }
            map.put(entry.getKey(), fault);
        }
        return map;
    }

    /**
     * 得到设备列表
     * @return
     * {
     *     "注塑机":{
     *         "注塑机1":"fksjl165161",
     *         "注塑机2":"fjobkp12356",
     *     },
     *     "水压测试仪":{
     *         "水压测试仪1":"abcd123456",
     *         "水压测试仪2":"ab32145"
     *     }
     * }
     */
    @Override
    public Map<String, Map<String, String>> getEquipList()
    {
        Map<String, Map<String, String>> equipMap = new HashMap<>();
        Map<String,String> equipSortMap=getEquipSortMap();
        List<String> sorts=new ArrayList<>();
        for (Map.Entry<String,String> entry:equipSortMap.entrySet())
        {
            sorts.add(entry.getValue());
        }
        List<Equip> equips=equipRepository.findAll();
        for (String s:sorts)
        {
            Map<String,String> map=new HashMap<>();
            for (Equip e:equips)
            {
                if (e.getEquipSort().equals(s))
                {
                    map.put(e.getEquipName(), e.getDeviceNum());
                }
            }
            equipMap.put(s, map);
        }
        return equipMap;
    }

    @Override
    public List<String> getDeviceNumList()
    {
        List<Equip> equips=equipRepository.findAll();
        List<String> list=new ArrayList<>();
        for (Equip equip:equips)
        {
            list.add(equip.getDeviceNum());
        }
        return list;
    }

    /**
     * 通过Rest接口得到设备名字列表
     * @return  Map<deviceNum,equipName>
     */
    private Map<String, String> getEquipNameMapByRest()
    {
        Map<String,String> equipNameMap=new HashMap<>();
        EquipSort equipSort = restTemplate.getForEntity(ConfigBean.equipSortUrl, EquipSort.class).getBody();
        for (Map<String, List<Map<String, List<RestEquipListData>>>> k : equipSort.getData())
        {
            //data中的子元素
            for (Map.Entry<String, List<Map<String, List<RestEquipListData>>>> m : k.entrySet())
            {
                //一车间中的子元素
                for (Map<String, List<RestEquipListData>> s : m.getValue())
                {
                    //注塑机中的子元素
                    for (Map.Entry<String, List<RestEquipListData>> entry : s.entrySet())
                    {
                        List<RestEquipListData> list = entry.getValue();
                        for (RestEquipListData r : list)
                        {
                            equipNameMap.put(r.getDeviceNum(), r.getDeviceName());
                        }
                    }
                }
            }
        }
        return equipNameMap;
    }

    /**
     * 通过Rest接口得到设备类别列表
     * @return Map<deviceNum,equipSort>
     */
    private Map<String, String> getEquipSortMapByRest()
    {
        Map<String,String> equipSortMap=new HashMap<>();
        EquipSort equipSort = restTemplate.getForEntity(ConfigBean.equipSortUrl, EquipSort.class).getBody();
        for (Map<String, List<Map<String, List<RestEquipListData>>>> k : equipSort.getData())
        {
            //data中的子元素
            for (Map.Entry<String, List<Map<String, List<RestEquipListData>>>> m : k.entrySet())
            {
                //一车间中的子元素
                for (Map<String, List<RestEquipListData>> s : m.getValue())
                {
                    //注塑机中的子元素
                    for (Map.Entry<String, List<RestEquipListData>> entry : s.entrySet())
                    {
                        List<RestEquipListData> list = entry.getValue();
                        for (RestEquipListData r : list)
                        {
                            equipSortMap.put(r.getDeviceNum(), entry.getKey());
                        }
                    }
                }
            }
        }
        return equipSortMap;
    }

    //得到deviceNum列表
    private List<String> getDeviceNumListByRest()
    {
        ReturnData data = restTemplate.getForEntity(ConfigBean.equipListUrl, ReturnData.class).getBody();
        System.out.println(data);
        String json=JSON.toJSONString(data.getData());
        List<RestEquipListData> list = JSON.parseArray(json, RestEquipListData.class);
        List<String> deviceNums=new ArrayList<>();
        for (RestEquipListData r:list)
        {
            deviceNums.add(r.getDeviceNum());
        }
        return deviceNums;
    }

    //根据RabbitMQ接收到的信息，判断设备状态（开机 1、关机  0）
    @Override
    public Integer judgeEquipStateByMsg(Signal signal)
    {
        Integer state = 0;
        if (signal.getData().getState() == null)
        {
            state = 0;
        }
        else
        {
            if (!signal.getData().getState().containsKey("startup"))
            {
                state = 0;
            }
            else
            {
                if (signal.getData().getState().get("startup").equals("1"))
                {
                    state = 1;
                }
                else
                {
                    state = 0;
                }
            }
        }
        return state;
    }
}
