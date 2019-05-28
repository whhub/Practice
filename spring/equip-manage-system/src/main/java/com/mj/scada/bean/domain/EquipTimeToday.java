package com.mj.scada.bean.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 *Author:ZouHeng
 *Des:设备当天的开机时间
 *Date:2019-04-02 18:05
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "equip_time_today")
public class EquipTimeToday
{
    @Id
    private String deviceNum;
    private String startupTime;
    private String shutdownTime;
    private Integer totalTime;

    public EquipTimeToday()
    {
    }

    public EquipTimeToday(String deviceNum, String startupTime, String shutdownTime, Integer totalTime)
    {
        this.deviceNum = deviceNum;
        this.startupTime = startupTime;
        this.shutdownTime = shutdownTime;
        this.totalTime = totalTime;
    }
}
