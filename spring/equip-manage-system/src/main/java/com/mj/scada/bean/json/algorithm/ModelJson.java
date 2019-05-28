package com.mj.scada.bean.json.algorithm;

import com.mj.scada.bean.domain.algorithm.AlgorithmModel;
import lombok.Data;

import java.util.List;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-05-22 16:53
 */
@Data
public class ModelJson
{
    private AlgorithmModel model;
    private List<NodeInfoJson> nodes;

    public ModelJson(AlgorithmModel model, List<NodeInfoJson> nodes)
    {
        this.model = model;
        this.nodes = nodes;
    }
}
