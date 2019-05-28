package com.mj.scada.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.catalina.LifecycleState;

import javax.print.DocFlavor;
import java.util.Dictionary;
import java.util.Map;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-04-25 11:13
 */
@Getter
@Setter
@ToString
public class ShowNode
{
    private String nodeName;
    private String paramsNum;
    private String nodeFurmula;
    private Map<String,String> params;
}
