package com.zhhe.lianying_project.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-13 10:58
 */
@Getter
@Setter
@ToString
public class WebSocketMsg5
{
    private String index;
    private Map<String,Integer> msg;
}
