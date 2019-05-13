package com.zhhe.lianying_project.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-01 15:51
 */
@Getter
@Setter
@ToString
public class Signal
{
    private String deviceNum;
    private String date;
    private SignalData data;
}
