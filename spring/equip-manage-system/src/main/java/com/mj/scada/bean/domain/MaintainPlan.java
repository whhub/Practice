package com.mj.scada.bean.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 *Author:ZouHeng
 *Des:维修计划
 *Date:2019-03-19 14:16
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "maintain_plan")
public class MaintainPlan
{
    @Id
    @GeneratedValue
    private Integer id;
    private String date;
    private Integer maintainInfo_id;
    private String status;
}
