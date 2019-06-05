package com.mj.scada.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 *Author:ZouHeng
 *Des:用于解析从RabbitMQ接收到的消息
 *Date:2019-03-18 15:57
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
