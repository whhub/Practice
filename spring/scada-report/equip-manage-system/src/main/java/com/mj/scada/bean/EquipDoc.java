package com.mj.scada.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/*
 *Author:ZouHeng
 *Des:返回给前端的设备档案及档案相关信息
 *Date:2019-03-29 15:29
 */
@Getter
@Setter
@ToString
public class EquipDoc
{
    private String deviceNum;
    private List<Map<String,String>> doc;
}
