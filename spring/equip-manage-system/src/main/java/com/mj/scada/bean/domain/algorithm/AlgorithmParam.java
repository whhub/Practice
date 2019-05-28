package com.mj.scada.bean.domain.algorithm;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/*
 *Author:ZouHeng
 *Des:节点参数
 *Date:2019-04-01 18:00
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "algorithm_param")
public class AlgorithmParam
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer nodeId;
    private String paramName;
    private String variable;        //变量：X
    private String connectType;     //关联数据的类型
    private String connectKeyName;  //关联数据

    public AlgorithmParam()
    {
    }

    public AlgorithmParam(Integer nodeId, String paramName, String variable, String connectType, String connectKeyName)
    {
        this.nodeId = nodeId;
        this.paramName = paramName;
        this.variable = variable;
        this.connectType = connectType;
        this.connectKeyName = connectKeyName;
    }
}
