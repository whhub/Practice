package com.mj.scada.service;

import com.mj.scada.bean.domain.algorithm.AlgorithmNode;
import com.mj.scada.bean.json.ReturnData;

import javax.script.ScriptException;
import java.util.List;
import java.util.Map;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-04-02 10:19
 */
public interface AlgorithmNodeService
{
    ReturnData addNode(String json);
    ReturnData delNode(Integer nodeId);
    ReturnData updateNode(String json);
    ReturnData deleteAllNode(Integer modelId);
    ReturnData seeNode(Integer nodeId);
    Map<String,String> getValue(Integer nodeId,String type) throws ScriptException;
    List<AlgorithmNode> findAllByModelId(Integer modelId);

}
