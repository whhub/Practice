package com.zhhe.lianying_project.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-02-27 10:43
 */
@Getter
@Setter
@ToString
public class RestEquipListData
{
    private Integer id;
    private String deviceName;
    private String deviceArea;
    private String deviceNum;
    private String controllerModel;
    private Integer templateID;
    private Integer workshopID;
    private String customFields;
}
