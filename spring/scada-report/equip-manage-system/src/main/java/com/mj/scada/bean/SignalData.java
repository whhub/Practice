package com.mj.scada.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

/*
 *Author:ZouHeng
 *Des:Signal类的子对象
 *Date:2019-03-18 15:58
 */
@Getter
@Setter
@ToString
public class SignalData
{
    private Map<String,String> process; //工艺类
    private Map<String,String> state;   //状态类
    private Map<String,String> energy;  //能耗类
    private Map<String,String> custom;  //自定义类
}
