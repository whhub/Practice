package com.zhhe.lianying_project.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-01 15:52
 */
@Getter
@Setter
@ToString
public class SignalData
{
    private Map<String,String> process;
    private Map<String,String> state;
}
