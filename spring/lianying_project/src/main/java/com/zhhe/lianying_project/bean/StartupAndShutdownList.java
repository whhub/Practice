package com.zhhe.lianying_project.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-07 9:39
 */
@Getter
@Setter
@ToString
public class StartupAndShutdownList
{
    private String index;
    private List<Map<String,String>> shutdownData;
    private List<Map<String,String>> startupData;
}
