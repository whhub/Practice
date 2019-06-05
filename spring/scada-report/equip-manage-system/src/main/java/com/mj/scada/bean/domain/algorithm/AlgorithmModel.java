package com.mj.scada.bean.domain.algorithm;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/*
 *Author:ZouHeng
 *Des:算法模型
 *Date:2019-04-01 16:14
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "algorithm_model")
public class AlgorithmModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String modelName;   //名称
    private String version;     //版本
    private Boolean isOpen;     //是否公开
    private String updateDate;  //更新日期
    private String msg;         //简介
    private String author;      //公司（作者）
    private String furmula;     //计算公式
    private Integer linkNum;    //寄存器关联
    private Integer dynamic;    //动态

    public AlgorithmModel()
    {
    }

    public AlgorithmModel(String modelName, String version, Boolean isOpen, String updateDate, String msg, String author, String furmula, Integer linkNum, Integer dynamic)
    {
        this.modelName = modelName;
        this.version = version;
        this.isOpen = isOpen;
        this.updateDate = updateDate;
        this.msg = msg;
        this.author = author;
        this.furmula = furmula;
        this.linkNum = linkNum;
        this.dynamic = dynamic;
    }
}
