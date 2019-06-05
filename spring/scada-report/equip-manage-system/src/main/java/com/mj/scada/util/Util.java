package com.mj.scada.util;

import com.mj.scada.bean.domain.TimeToday;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
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
     * @param backMsg "year"  "month"  "date" "hour"  "min"   "second"
     * @return
     */
    public static Integer getCurrentYearOrTime(String backMsg)
    {
        String nowTime = getCurrentDateAndTime();
        switch (backMsg)
        {
            case "year":
                return Integer.parseInt(nowTime.split(" ")[0].split("-")[0]);
            case "month":
                return Integer.parseInt(nowTime.split(" ")[0].split("-")[1]);
            case "date":
                return Integer.parseInt(nowTime.split(" ")[0].split("-")[2]);
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

    //获取上周的日期集合
    public static List<String> getLastWeekDates()
    {
        LocalDate today = LocalDate.now();
        DayOfWeek dayOfWeek = today.getDayOfWeek();//获取今天是周几
        LocalDate lastMonday = today.minusDays(7+dayOfWeek.getValue()-1);//算出上周一
        String lastTuesday=lastMonday.with(DayOfWeek.TUESDAY).toString();
        String lastWednesday=lastMonday.with(DayOfWeek.WEDNESDAY).toString();
        String lastThursday=lastMonday.with(DayOfWeek.THURSDAY).toString();
        String lastFriday=lastMonday.with(DayOfWeek.FRIDAY).toString();
        String lastSaturday=lastMonday.with(DayOfWeek.SATURDAY).toString();
        String lastSunday=lastMonday.with(DayOfWeek.SUNDAY).toString();
        List<String> list = new ArrayList<>();
        list.add(lastMonday.toString());
        list.add(lastTuesday);
        list.add(lastWednesday);
        list.add(lastThursday);
        list.add(lastFriday);
        list.add(lastSaturday);
        list.add(lastSunday);
        return list;
    }

    //获取这周的日期集合
    public static List<String> getThisWeekDates()
    {
        LocalDate today = LocalDate.now();
        String monday=today.with(DayOfWeek.MONDAY).toString();
        String tuesDay=today.with(DayOfWeek.TUESDAY).toString();
        String wednesday=today.with(DayOfWeek.WEDNESDAY).toString();
        String thursday=today.with(DayOfWeek.THURSDAY).toString();
        String friday=today.with(DayOfWeek.FRIDAY).toString();
        String saturday=today.with(DayOfWeek.SATURDAY).toString();
        String sunday=today.with(DayOfWeek.SUNDAY).toString();
        List<String> list = new ArrayList<>();
        list.add(monday);
        list.add(tuesDay);
        list.add(wednesday);
        list.add(thursday);
        list.add(friday);
        list.add(saturday);
        list.add(sunday);
        return list;
    }

    /**
     * 得到日期区间
     * @return date2-date1区间
     * 如date1=2019-05-25，date2=2019-05-28，
     * list元素：2019-05-25、2019-05-26、2019-05-27、2019-05-28
     */
    public static List<String> getDateSection(String startDate,String endDate) throws ParseException
    {
        Date start=new SimpleDateFormat("yyyy-mm-dd").parse(startDate);
        Date end=new SimpleDateFormat("yyyy-mm-dd").parse(endDate);
        List<Date> result = new ArrayList<>();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(start);

        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(end);
        while (tempStart.before(tempEnd)) {
            result.add(tempStart.getTime());
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        result.add(end);
        System.out.println(result.size());
        List<String> list = new ArrayList<>();
        for (Date date:result)
        {
            String s=new SimpleDateFormat("yyyy-MM-dd").format(date);
            list.add(s);
        }
        return list;
    }

//    //从接口得到对应deviceNum的相关信息
//    public static RestEquipListData getRestEquipListData(RestTemplate restTemplate, String deviceNum)
//    {
//        EquipSort equipSort = restTemplate.getForEntity(ConfigBean.equipSortUrl, EquipSort.class).getBody();
//        for (Map<String, List<Map<String, List<RestEquipListData>>>> k : equipSort.getData())
//        {
//            for (Map.Entry<String, List<Map<String, List<RestEquipListData>>>> m : k.entrySet())
//            {
//                for (Map<String, List<RestEquipListData>> s : m.getValue())
//                {
//                    for (Map.Entry<String, List<RestEquipListData>> entry : s.entrySet())
//                    {
//                        List<RestEquipListData> list = entry.getValue();
//                        for (RestEquipListData r : list)
//                        {
//                            if (r.getDeviceNum().equals(deviceNum))
//                            {
//                                return r;
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return null;
//    }


    //根据数据库中的数据，判断设备状态
    public static Integer judgeEquipStateByDatabase(TimeToday e)
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

    /**
     * 根据当前时间，得到当天24点的日期和时间
     *
     * @param time 当前时间，格式：2019-03-05 11:14:11
     * @return 当天24点的日期和时间，格式：2019-03-05 23:59:59
     */
    public static String getEndDate(String time)
    {
        String date = time.split(" ")[0];
        String nowDate = date + " 3:59:59";
        return nowDate;
    }
}
