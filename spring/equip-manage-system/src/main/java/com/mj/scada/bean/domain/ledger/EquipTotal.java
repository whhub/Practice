package com.mj.scada.bean.domain.ledger;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/*
 *Author:ZouHeng
 *Des:历年设备总数
 *Date:2019-03-20 9:35
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "equip_total")
public class EquipTotal
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer total;
    private Integer year;

    public EquipTotal()
    {
    }

    public EquipTotal(Integer total, Integer year)
    {
        this.total = total;
        this.year = year;
    }
}
