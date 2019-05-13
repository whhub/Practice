package com.zhhe.lianying_project.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/*
 *Author:ZouHeng
 *Des:传给乃欣的websocket信号信息
 *Date:2019-03-06 13:56
 */
@Getter
@Setter
@ToString
public class WebSocketMsg
{
    private String index;
    private List<WebSocketMsgChild> msg;
}
