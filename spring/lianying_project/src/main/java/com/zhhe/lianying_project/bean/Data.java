package com.zhhe.lianying_project.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/*
 *Author:ZouHeng
 *Des:WebSocket发送给前端的数据
 *Date:2019-02-22 9:55
 */
@Getter
@Setter
@ToString
public class Data
{
    //设备信息
    private String deviceName;
    private String deviceNum;
    private Integer id;
    private String controllerModel;
    private String deviceArea;

    //状态类
    private Integer test1;
    private Integer test2;
    private Integer test3;
    private Integer test4;
    private Integer test5;
    private Integer test6;

    //利用率(周一到周日)
    private Integer localOee1;  //本机
    private Integer localOee2;
    private Integer localOee3;
    private Integer localOee4;
    private Integer localOee5;
    private Integer localOee6;
    private Integer localOee7;
    private Integer averOee1;  //平均
    private Integer averOee2;
    private Integer averOee3;
    private Integer averOee4;
    private Integer averOee5;
    private Integer averOee6;
    private Integer averOee7;

    //利用率下的停机分析
    private Integer maxFault;    //故障
    private Integer maxChange;   //换膜
    private Integer maxRepair;   //维修
    private Integer maxStarving; //缺料
    private Integer maxPlan;     //计划
    private Integer maxMaintain; //保养
    private Integer localFault;     //本机停机分析
    private Integer localChange;
    private Integer localRepair;
    private Integer localStarving;
    private Integer localPlan;
    private Integer localMaintain;
    private Integer averFault;      //平均停机原因
    private Integer averChange;
    private Integer averRepair;
    private Integer averStarving;
    private Integer averPlan;
    private Integer averMaintain;

    //三相电
    private Double voltageATotal;
    private Double voltageA;   //A相电压
    private Double electricA;  //A相电流
    private Double voltageBTotal;
    private Double voltageB;
    private Double electricB;
    private Double voltageCTotal;
    private Double voltageC;
    private Double electricC;

    //能耗分析
    private Integer power1;  //功率（周一）
    private Integer power2;
    private Integer power3;
    private Integer power4;
    private Integer power5;
    private Integer power6;
    private Integer power7;
    private Double energyCons1;  //能耗（周一）
    private Double energyCons2;
    private Double energyCons3;
    private Double energyCons4;
    private Double energyCons5;
    private Double energyCons6;
    private Double energyCons7;

    //工艺监控
    private Integer setValue1;  //设置值
    private Integer setValue2;
    private Integer setValue3;
    private Integer setValue4;
    private Integer setValue5;
    private Integer setValue6;
    private Integer setValue7;
    private Integer setValue8;
    private Integer setValue9;
    private Integer setValue10;
    private Integer setValue11;
    private Integer setValue12;
    private Integer setValue13;
    private Integer setValue14;
    private Integer actualValue1;   //实际值
    private Integer actualValue2;
    private Integer actualValue3;
    private Integer actualValue4;
    private Integer actualValue5;
    private Integer actualValue6;
    private Integer actualValue7;
    private Integer actualValue8;
    private Integer actualValue9;
    private Integer actualValue10;
    private Integer actualValue11;
    private Integer actualValue12;
    private Integer actualValue13;
    private Integer actualValue14;

    //工艺类
    private List<String> keyNameList;
    private List<Integer> keyValueList;
    private List<String> timeList;
    private List<String> valueList;
}
