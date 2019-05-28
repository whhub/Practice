package com.mj.scada.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-18 15:47
 */
@Getter
@Setter
@ToString
public class EquipSort
{
    private Integer code;
    private String msg;
    private List<Map<String,List<Map<String,List<RestEquipListData>>>>> data;
}
