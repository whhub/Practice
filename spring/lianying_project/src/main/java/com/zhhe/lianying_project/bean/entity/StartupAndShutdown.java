package com.zhhe.lianying_project.bean.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 *Author:ZouHeng
 *Des:整点时刻的设备开关机数量
 *Date:2019-03-12 13:52
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "ssd_startup_shutdown")
public class StartupAndShutdown
{
    @Id
    private Integer id;
    private Integer startupNum;
    private Integer shutdownNum;
    private Integer time;   //整点
}
