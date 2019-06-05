package com.mj.scada.bean.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-05-09 15:58
 */
@Getter
@Setter
@ToString
public class TestExcel
{
    private Integer id;
    private String name;
    private String username;
    private String create_time;

    public TestExcel(Integer id, String name, String username, String create_time)
    {
        this.id = id;
        this.name = name;
        this.username = username;
        this.create_time = create_time;
    }
}
