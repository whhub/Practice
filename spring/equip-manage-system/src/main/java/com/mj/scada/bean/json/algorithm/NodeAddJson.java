package com.mj.scada.bean.json.algorithm;

import com.mj.scada.bean.domain.algorithm.AlgorithmParam;
import lombok.Data;

import java.util.List;

/*
 *Author:ZouHeng
 *Des:添加算法节点的json
 *Date:2019-05-22 14:31
 */
@Data
public class NodeAddJson
{
    private Integer modelId;
    private String nodeName;
    private String furmula;
    private List<AlgorithmParam> params;
}
