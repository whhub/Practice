package com.mj.scada.bean.json;

import lombok.Data;

/*
 *Author:ZouHeng
 *Des:主页中的设备状态监控详细信息
 *Date:2019-05-28 15:54
 */
@Data
public class StatusInfoJson
{
    private String equipName;
    private String deviceNum;
    private String date;
    private Boolean isUp;
    private Integer openTimeNow;    //到现在时刻，设备的开机时间
    private String openRatio;       //openTimeNow/今天到现在时刻总时间*100%
    private Integer stateCode;      //0：关机  1：运行中    2：故障

    public StatusInfoJson(String equipName, String deviceNum, String date, Boolean isUp, Integer openTimeNow, String openRatio, Integer stateCode)
    {
        this.equipName = equipName;
        this.deviceNum = deviceNum;
        this.date = date;
        this.isUp = isUp;
        this.openTimeNow = openTimeNow;
        this.openRatio = openRatio;
        this.stateCode = stateCode;
    }
}
