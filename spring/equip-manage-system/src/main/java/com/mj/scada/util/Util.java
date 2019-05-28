package com.mj.scada.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mj.scada.bean.RestEquipListData;
import com.mj.scada.bean.domain.EquipTimeToday;
import com.mj.scada.bean.EquipSort;
import com.mj.scada.bean.Signal;
import com.mj.scada.config.ConfigBean;
import com.mj.scada.rabbitmq.Consumer;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.*;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-18 15:41
 */
@Component
public class Util
{
    private static SimpleDateFormat df;

//    Map<deviceNum,deviceName>
    public static Map<String,String> equipNameMap=new HashMap<>();
//    Map<deviceNum,deviceSort>
    public static Map<String,String> equipSortMap=new HashMap<>();

    static
    {
        df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置日期格式
    }

    //获取当前日期和时间（时间格式：2019-03-04 17:16:05）
    public static String getCurrentDateAndTime()
    {
        return df.format(new Date());
    }

    //获取当前的日期或时间
    public static String getCurrentDateOrTime(String backMsg)
    {
        switch (backMsg)
        {
            case "date":
                return getCurrentDateAndTime().split(" ")[0];
            case "time":
                return getCurrentDateAndTime().split(" ")[1];
            default:
                return null;
        }
    }

    /**
     * 获取年份或时间
     * @param backMsg "year"   "hour"  "min"   "second"
     * @return
     */
    public static Integer getCurrentYearOrTime(String backMsg)
    {
        String nowTime = getCurrentDateAndTime();
        switch (backMsg)
        {
            case "year":
                return Integer.parseInt(nowTime.split(" ")[0].split("-")[0]);
            case "hour":
                return Integer.parseInt(nowTime.split(" ")[1].split(":")[0]);
            case "min":
                return Integer.parseInt(nowTime.split(" ")[1].split(":")[1]);
            case "second":
                return Integer.parseInt(nowTime.split(" ")[1].split(":")[2]);
            default:
                return -1;
        }
    }

    //从接口得到对应deviceNum的相关信息
    public static RestEquipListData getRestEquipListData(RestTemplate restTemplate, String deviceNum)
    {
        EquipSort equipSort = restTemplate.getForEntity(ConfigBean.equipSortUrl, EquipSort.class).getBody();
        for (Map<String, List<Map<String, List<RestEquipListData>>>> k : equipSort.getData())
        {
            for (Map.Entry<String, List<Map<String, List<RestEquipListData>>>> m : k.entrySet())
            {
                for (Map<String, List<RestEquipListData>> s : m.getValue())
                {
                    for (Map.Entry<String, List<RestEquipListData>> entry : s.entrySet())
                    {
                        List<RestEquipListData> list = entry.getValue();
                        for (RestEquipListData r : list)
                        {
                            if (r.getDeviceNum().equals(deviceNum))
                            {
                                return r;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * 同步map，得到设备名字或设备所属类别
     * @param restTemplate
     * equipNameMap是Map<deviceNum,deviceName>；
     * equipSortMap是Map<deviceNum,deviceSort>；
     */
    public static void getEquipMap(RestTemplate restTemplate)
    {
        equipNameMap.clear();
        equipSortMap.clear();
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
                            equipSortMap.put(r.getDeviceNum(), entry.getKey());
                        }
                    }
                }
            }
        }
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
    public static String getEquipList(RestTemplate restTemplate)
    {
        EquipSort equipSort = restTemplate.getForEntity(ConfigBean.equipSortUrl, EquipSort.class).getBody();
        Map<String, Map<String, String>> equipMap = new HashMap<>();
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
                            if (equipMap.containsKey(m.getKey()))
                            {
                                equipMap.get(m.getKey()).put(r.getDeviceName(), r.getDeviceNum());
                            }
                            else
                            {
                                Map<String, String> map = new HashMap<>();
                                equipMap.put(entry.getKey(), map);
                                map.put(r.getDeviceName(), r.getDeviceNum());
                            }

                        }
                    }
                }
            }
        }
        return JSON.toJSONString(equipMap);
    }

    /**
     * 获取某类型的所有设备
     * @param type  设备类型
     * @return  list中的元素是设备的deviceNum
     */
    public static List<String> getEquipSortList(String type)
    {
        List<String> list = new ArrayList<>();
        for (Map.Entry<String,String> entry:equipSortMap.entrySet())
        {
            if (entry.getValue().equals(type))
            {
                list.add(entry.getKey());
            }
        }
        return list;
    }

    //根据RabbitMQ接收到的信息，判断设备状态（开机 1、关机  0）
    public static Integer judgeEquipStateByMsg(Signal signal)
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

    //根据数据库中的数据，判断设备状态
    public static Integer judgeEquipStateByDatabase(EquipTimeToday e)
    {
        if (e.getStartupTime() == null && e.getShutdownTime() != null)
        {
            //关机
            return 0;
        }
        else if (e.getShutdownTime() == null && e.getStartupTime() != null)
        {
            //开机
            return 1;
        }
        else
        {
            return -1;  //数据错误
        }
    }

    //得到所有设备的开关机状态(0：关机  1：开机）
    public static Map<String,Integer> getEquipsStartup()
    {
        Map<String,Integer> map=new HashMap<>();

        //遍历是否所有设备都接收到了消息
        for (Map.Entry<String,String> entry:equipNameMap.entrySet())
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
    public static Map<String,Integer> getEquipsFault()
    {
        Map<String,Integer> map=new HashMap<>();
        //遍历是否所有设备都接收到了消息
        for (Map.Entry<String,String> entry:equipNameMap.entrySet())
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
     * 计算时间差值(time2-time1)
     *
     * @param time1 格式：2019-03-04 17:16:05
     * @param time2 格式：同上
     * @return 相差的分钟数（单位：分，数据类型Integer，不会考虑日期的影响，只对日期后面的时间进行计算）
     */
    public static Integer calculateTime(String time1, String time2)
    {
        //时间计算
        String[] timeArray1 = (time1.split(" ")[1]).split(":");
        String[] timeArray2 = (time2.split(" ")[1]).split(":");
        if (timeArray2[0] == "00" && timeArray2[1] == "00" && timeArray2[2] == "00")
        {
            timeArray2[0] = "24";
        }
        Integer[] t1 = new Integer[3];
        Integer[] t2 = new Integer[3];
        for (int i = 0; i < 3; i++)
        {
            t1[i] = Integer.parseInt(timeArray1[i]);
            t2[i] = Integer.parseInt(timeArray2[i]);
        }
        Integer time = (t2[0] - t1[0]) * 60 + (t2[1] - t1[1]);
        return time;
    }

    /**
     * 根据当前时间，得到当天零点的日期和时间
     *
     * @param time 当前时间，格式：2019-03-05 11:14:11
     * @return 当天零点的日期和时间，格式：2019-03-05 00:00:00
     */
    public static String getZeroDate(String time)
    {
        String date = time.split(" ")[0];
        String nowDate = date + " 00:00:00";
        return nowDate;
    }
}
