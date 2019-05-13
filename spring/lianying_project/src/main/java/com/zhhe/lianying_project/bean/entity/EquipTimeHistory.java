package com.zhhe.lianying_project.bean.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-05 10:37
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "sdd_startup_history")
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
