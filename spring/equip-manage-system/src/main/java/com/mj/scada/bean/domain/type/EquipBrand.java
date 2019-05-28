package com.mj.scada.bean.domain.type;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/*
 *Author:ZouHeng
 *Des:设备品牌
 *Date:2019-03-27 10:23
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "equip_brand")
public class EquipBrand
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer typeId;         //类别ID
    private String brandName;       //品牌名称
    private String brandFullName;   //品牌全称
    private Integer sortNum;        //排序号
    private String catalogueType;   //目录类别
    private String status;          //状态
    private String remark;          //备注

    public EquipBrand()
    {
    }

    public EquipBrand(Integer id, Integer typeId, String brandName, String brandFullName, Integer sortNum, String catalogueType, String status, String remark)
    {
        this.id = id;
        this.typeId = typeId;
        this.brandName = brandName;
        this.brandFullName = brandFullName;
        this.sortNum = sortNum;
        this.catalogueType = catalogueType;
        this.status = status;
        this.remark = remark;
    }
}
