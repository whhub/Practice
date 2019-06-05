package com.mj.scada.bean.json.algorithm;

import com.mj.scada.bean.domain.algorithm.AlgorithmNode;
import com.mj.scada.bean.domain.algorithm.AlgorithmParam;
import lombok.Data;

import java.util.List;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-05-22 16:45
 */
@Data
public class NodeInfoJson
{
    private AlgorithmNode node;
    private List<AlgorithmParam> params;

    public NodeInfoJson(AlgorithmNode node, List<AlgorithmParam> params)
    {
        this.node = node;
        this.params = params;
    }
}
