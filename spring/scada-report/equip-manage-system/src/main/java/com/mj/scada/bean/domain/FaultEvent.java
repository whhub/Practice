package com.mj.scada.bean.domain;

import lombok.Data;

import javax.persistence.*;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-05-29 15:34
 */
@Entity
@Data
@Table(name = "fault_event")
public class FaultEvent
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String deviceNum;
    private String type;        //故障类型（故障码）
    private String todayDate;       //当天日期
    private String startDate;
    private String endDate;
    private Integer totalTime;
}
