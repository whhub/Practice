package com.mj.scada.bean.domain.statistics;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/*
 *Author:ZouHeng
 *Des:统计表中的元素
 * 关联的数据如果是“算法模型”：
 * 关联的数据如果不是"算法模型"，则到equip_data表中查询相关数据（开机时间数据未存储，需要实时计算获得）
 *Date:2019-04-10 14:04
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "statistic_table_param")
public class StatisticTableParam
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String tableName;
    private String paramName;
    private Boolean isUse;
    private String keyName;
    private String paramType;
    private String connectType;
    private String connectName;
    private String fixedValue;  //固定值

    public void setValue(String tableName, String paramName, Boolean isUse,String keyName,String paramType,String connectType,String connectName,String fixedValue)
    {
        this.tableName=tableName;
        this.paramName=paramName;
        this.isUse=isUse;
        this.keyName=keyName;
        this.paramType=paramType;
        this.connectType=connectType;
        this.connectName=connectName;
        this.fixedValue=fixedValue;
    }
}
