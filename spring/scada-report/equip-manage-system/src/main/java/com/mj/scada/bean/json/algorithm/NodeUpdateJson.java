package com.mj.scada.bean.json.algorithm;

import com.mj.scada.bean.domain.algorithm.AlgorithmParam;
import lombok.Data;

import java.util.List;

/*
 *Author:ZouHeng
 *Des:修改算法节点提交的json
 *Date:2019-05-22 14:48
 */
@Data
public class NodeUpdateJson
{
    private Integer id;
    private Integer modelId;
    private String nodeName;
    private String furmula;
    private List<AlgorithmParam> addParams;
    private List<Integer> delParams;
    private List<AlgorithmParam> updateParams;
}
