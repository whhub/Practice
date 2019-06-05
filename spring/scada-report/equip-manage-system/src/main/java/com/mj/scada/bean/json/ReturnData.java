package com.mj.scada.bean.json;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-05-07 17:28
 */
@Getter
@Setter
@ToString
public class ReturnData
{
    private Integer code;
    private String msg;
    private Object data;

    public ReturnData()
    {
    }

    public ReturnData(Integer code, String msg, Object data)
    {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
