package com.mj.scada.bean.domain.statistics;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-04-29 14:55
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "sta_table")
public class StaTable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String tableName;
    private String createTime;
    private Integer dataNum;
    private String remarks;

    public StaTable()
    {
    }

    public StaTable(String tableName, String createTime, Integer dataNum, String remarks)
    {
        this.tableName = tableName;
        this.createTime = createTime;
        this.dataNum = dataNum;
        this.remarks = remarks;
    }
}
