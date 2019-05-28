package com.mj.scada.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 *Author:ZouHeng
 *Des:调用的底层Rest接口的地址
 *Date:2019-03-18 15:49
 */
@Getter
@Setter
@ToString
public class ConfigBean
{
    public static String equipListUrl="http://10.168.4.59:4000/basics/deviceInfo/infos";    //查询所有设备信息
    public static String equipSortUrl="http://10.168.4.59:4000/basics/deviceInfo/treeAll";
    public static String waterTesterDeviceNum1="2019030100672660877D";
}
