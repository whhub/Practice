package com.mj.scada.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-02-27 10:41
 */
@Getter
@Setter
@ToString
public class RestEquipList
{
    private Integer code;
    private String msg;
    private List<RestEquipListData> data;
}
