package com.mj.scada.bean.domain.type;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/*
 *Author:ZouHeng
 *Des:设备类别
 *Date:2019-03-27 10:14
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "equip_type")
public class EquipType
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String addWay;          //新增方式
    private String typeName;        //类别名称
    private String typeFullName;    //类别全称
    private Integer sortNum;        //排序号
    private String catalogueType;   //目录类别
    private String updateTime;      //更新时间
    private String remark;          //备注
    private String status;          //状态

    public EquipType()
    {
    }

    public EquipType(String addWay, String typeName, String typeFullName, Integer sortNum, String catalogueType, String updateTime, String remark, String status)
    {
        this.addWay = addWay;
        this.typeName = typeName;
        this.typeFullName = typeFullName;
        this.sortNum = sortNum;
        this.catalogueType = catalogueType;
        this.updateTime = updateTime;
        this.remark = remark;
        this.status = status;
    }
}
