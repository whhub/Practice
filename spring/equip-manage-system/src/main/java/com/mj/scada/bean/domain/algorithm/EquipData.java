package com.mj.scada.bean.domain.algorithm;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 *Author:ZouHeng
 *Des:设备底层源数据
 *Date:2019-04-03 16:13
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "equip_data")
public class EquipData
{
    @Id
    private Integer id;
    private String deviceNum;
    private String type;
    private String keyName;
    private String value;
    private String reserve; //预留

    public EquipData()
    {
    }

    public EquipData(Integer id, String deviceNum, String type, String keyName, String value, String reserve)
    {
        this.id = id;
        this.deviceNum = deviceNum;
        this.type = type;
        this.keyName = keyName;
        this.value = value;
        this.reserve = reserve;
    }

    //导入类数据的构造
    public EquipData(String type, String keyName,String value)
    {
        this.type = type;
        this.keyName = keyName;
        this.value=value;
    }
}
