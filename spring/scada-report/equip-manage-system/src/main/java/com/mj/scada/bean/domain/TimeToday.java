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
@Table(name = "time_today")
public class TimeToday
{
    @Id
    private String deviceNum;
    private String startupTime;
    private String shutdownTime;
    private Integer totalTime;  //开机时间

    public TimeToday()
    {
    }

    public TimeToday(String deviceNum, String startupTime, String shutdownTime, Integer totalTime)
    {
        this.deviceNum = deviceNum;
        this.startupTime = startupTime;
        this.shutdownTime = shutdownTime;
        this.totalTime = totalTime;
    }
}
