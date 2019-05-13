package com.zhhe.lianying_project.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-06 13:57
 */
@Getter
@Setter
@ToString
public class WebSocketMsgChild
{
    private String name;
    private String deviceNum;
    private SignalData data;
}
