package com.mj.scada.bean.json;

import com.mj.scada.bean.domain.statistics.StatisticTableParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/*
 *Author:ZouHeng
 *Des:  getTableDetail  接口返回值
 *Date:2019-05-08 11:27
 */
@Getter
@Setter
@ToString
public class TableDetail
{
    private Integer Id;
    private String tableName;
    private String remarks;
    private List<StatisticTableParam> paramList;
}
