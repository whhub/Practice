package com.zhhe.lianying_project.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/*
 *Author:ZouHeng
 *Des:第三块的数据（整点的开关机数量）
 *Date:2019-03-12 14:32
 */
@Getter
@Setter
@ToString
public class WebSocketMsg3
{
    private String index;
    private List<Map<String,String>> msg;
}
