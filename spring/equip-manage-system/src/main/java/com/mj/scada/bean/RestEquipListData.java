package com.mj.scada.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-18 15:47
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
