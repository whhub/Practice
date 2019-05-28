package com.mj.scada.bean.json;

import com.mj.scada.bean.domain.statistics.StatisticTableParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-05-07 18:12
 */
@Getter
@Setter
@ToString
public class UpdateTableData
{
    private Integer id;
    private String tableName;
    private String tableMsg;
    private List<StatisticTableParam> updateTableParam;
    private List<StatisticTableParam> addTableParam;
    private List<StatisticTableParam> deleteTableParam;
}
