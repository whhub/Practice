package com.mj.scada.bean.domain;

import lombok.Data;

import javax.persistence.*;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-05-29 13:42
 */
@Entity
@Data
@Table(name = "fault_code")
public class FaultCode
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer typeId;
    private Integer brandId;
    private String code;        //故障代码
    private Boolean isUse;      //是否启用
    private String faultInfo;   //故障说明
    private String remark;      //备注

    public FaultCode()
    {
    }

}
