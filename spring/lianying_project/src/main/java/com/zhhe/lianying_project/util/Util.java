package com.zhhe.lianying_project.util;

import com.alibaba.fastjson.JSONObject;
import com.zhhe.lianying_project.bean.ConfigBean;
import com.zhhe.lianying_project.bean.EquipSort;
import com.zhhe.lianying_project.bean.RestEquipListData;
import com.zhhe.lianying_project.bean.Signal;
import com.zhhe.lianying_project.bean.entity.EquipTimeToday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-04 16:59
 */
@Component
public class Util
{
    private static SimpleDateFormat df;

    static {
        df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    }

    //获得当前时间（时间格式：2019-03-04 17:16:05）
    public static String getCurrentTime()
    {
        return df.format(new Date());
    }

    /**
     * 计算时间差值(time2-time1)
     * @param time1 格式：2019-03-04 17:16:05
     * @param time2 格式：同上
     * @return 相差的分钟数（单位：分，数据类型Integer，不会考虑日期的影响，只对日期后面的时间进行计算）
     */
    public static Integer calculateTime(String time1,String time2)
    {
        //时间计算
        String[] timeArray1=(time1.split(" ")[1]).split(":");
        String[] timeArray2=(time2.split(" ")[1]).split(":");
        if (timeArray2[0]=="00"&&timeArray2[1]=="00"&&timeArray2[2]=="00")
        {
            timeArray2[0]="24";
        }
        Integer[] t1=new Integer[3];
        Integer[] t2=new Integer[3];
        for (int i=0;i<3;i++)
        {
            t1[i]=Integer.parseInt(timeArray1[i]);
            t2[i]=Integer.parseInt(timeArray2[i]);
        }
        Integer time=(t2[0]-t1[0])*60+(t2[1]-t1[1]);
        return time;
    }

    //根据RabbitMQ接收到的信息，判断设备状态（开机、关机）
    public static Integer judgeEquipStateByMsg(Signal signal)
    {
        Integer state=0;
        if (signal.getData().getState() == null)
        {
            state=0;
        }
        else
        {
            if (!signal.getData().getState().containsKey("startup"))
            {
                state=0;
            }
            else
            {
                if (signal.getData().getState().get("startup").equals("1"))
                {
                    state=1;
                }
                else
                {
                    state=0;
                }
            }
        }
        return state;
    }

    //根据数据库中的数据，判断设备状态
    public static Integer judgeEquipStateByDatabase(EquipTimeToday e)
    {
        if (e.getStartupTime()==null&&e.getShutdownTime()!=null)
        {
            //关机
            return 0;
        }
        else if (e.getShutdownTime()==null&&e.getStartupTime()!=null)
        {
            //开机
            return 1;
        }
        else
        {
            return -1;  //数据错误
        }
    }

    /**
     * 根据当前时间，得到当天零点的日期和时间
     * @param time 当前时间，格式：2019-03-05 11:14:11
     * @return 当天零点的日期和时间，格式：2019-03-05 00:00:00
     */
    public static String getZeroDate(String time)
    {
        String date=time.split(" ")[0];
        String nowDate=date+" 00:00:00";
        return nowDate;
    }

    //从Get接口获取数据
    public static JSONObject getRest(String url,RestTemplate restTemplate)
    {
        JSONObject jsonObject = restTemplate.getForEntity(url, JSONObject.class).getBody();
        return jsonObject;
    }

    //从接口得到所有设备deviceNum和Name对应的Map
    public static Map<String, String> getEquipName(RestTemplate restTemplate)
    {
        EquipSort equipSort = restTemplate.getForEntity(ConfigBean.equipSortUrl, EquipSort.class).getBody();
        Map<String, String> equipMap = new HashMap<>();  //存储deviceNum和对应的设备名称
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
                            equipMap.put(r.getDeviceNum(), entry.getKey());
                        }
                    }
                }
            }
        }
        return equipMap;
    }
}
