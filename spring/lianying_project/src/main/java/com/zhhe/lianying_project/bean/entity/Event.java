package com.zhhe.lianying_project.bean.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-11 15:00
 */
@Getter
@Setter
@ToString
public class Event
{
    private String type;
    private String equipName;
    private String deviceNum;
    private String time;

    public Event(String type, String equipName, String deviceNum, String time)
    {
        this.type = type;
        this.equipName = equipName;
        this.deviceNum = deviceNum;
        this.time = time;
    }
}
