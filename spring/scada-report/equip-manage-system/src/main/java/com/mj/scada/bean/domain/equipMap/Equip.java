package com.mj.scada.bean.domain.equipMap;

import lombok.Data;
import lombok.Generated;
import lombok.Getter;

import javax.persistence.*;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-05-28 15:00
 */
@Entity
@Data
@Table(name = "equip")
public class Equip
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String deviceNum;
    private String equipName;   //设备名称
    private String equipSort;   //设备类别

    public Equip()
    {
    }

    public Equip(String deviceNum, String equipName, String equipSort)
    {
        this.deviceNum = deviceNum;
        this.equipName = equipName;
        this.equipSort = equipSort;
    }
}
