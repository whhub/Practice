package com.mj.scada.bean.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/*
 *Author:ZouHeng
 *Des:设备历史开机时间
 *Date:2019-04-02 18:06
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "equip_time_history")
public class EquipTimeHistory
{
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String deviceNum;
    @Column(nullable = false)
    private String date;
    @Column(nullable = false)
    private Integer startupTime;
    @Column(nullable = false)
    private Integer shutdownTime;
}
