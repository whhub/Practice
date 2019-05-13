package com.zhhe.lianying_project.bean.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-04 15:20
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "sdd_startup_today")
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
