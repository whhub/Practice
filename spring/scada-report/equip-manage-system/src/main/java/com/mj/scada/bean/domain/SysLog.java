package com.mj.scada.bean.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-06-01 14:09
 */
@Data
@Entity
@Table(name = "sys_log")
public class SysLog implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;    //用户名
    private String operation;   //操作
    private String method;      //方法名
//    private String params;      //参数
//    private String ip;          //IP地址
    private String createDate;    //操作时间
}
