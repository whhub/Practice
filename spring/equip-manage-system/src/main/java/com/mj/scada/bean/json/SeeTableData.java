package com.mj.scada.bean.json;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-05-10 11:12
 */
@Getter
@Setter
@ToString
public class SeeTableData
{
    private String paramType;
    private Map<String,String> data;

    public SeeTableData(String paramType, Map<String, String> data)
    {
        this.paramType = paramType;
        this.data = data;
    }
}
