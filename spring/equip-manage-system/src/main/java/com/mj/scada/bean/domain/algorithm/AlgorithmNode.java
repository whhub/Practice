package com.mj.scada.bean.domain.algorithm;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Map;

/*
 *Author:ZouHeng
 *Des:计算节点
 *Date:2019-04-01 17:58
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "algorithm_node")
public class AlgorithmNode
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer modelId;
    private String nodeName;
    private String furmula;         //计算公式

    public AlgorithmNode()
    {
    }

    public AlgorithmNode(Integer modelId, String nodeName, String furmula)
    {
        this.modelId = modelId;
        this.nodeName = nodeName;
        this.furmula = furmula;
    }
}
