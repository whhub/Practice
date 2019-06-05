package com.mj.scada.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-04-30 15:21
 */
@Getter
@Setter
@ToString
public class AddStaTable
{
    private String tableName;
    private String createTime;
    private List<Map<String,String>> data;
}
