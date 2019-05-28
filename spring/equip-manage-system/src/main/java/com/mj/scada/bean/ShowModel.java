package com.mj.scada.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-04-25 11:04
 */
@Getter
@Setter
@ToString
public class ShowModel
{
    private String modelName;
    private String modelFurmula;
    private List<ShowNode> nodes;
}
