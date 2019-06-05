package com.mj.scada.service;

import com.mj.scada.bean.domain.algorithm.AlgorithmParam;
import com.mj.scada.bean.json.ReturnData;

import java.util.List;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-04-02 10:20
 */
public interface AlgorithmParamService
{
    ReturnData addParam(AlgorithmParam param);
    void deleteAllParam(Integer nodeId);
    ReturnData deleteParam(Integer paramId);
    ReturnData updateParam(AlgorithmParam param);
    Boolean judgeName(List<AlgorithmParam> addParams,List<AlgorithmParam> updateParams);
    List<AlgorithmParam> findNodeParam(Integer nodeId);
    Boolean judgeAddName(AlgorithmParam param);
    Boolean judgeUpdateName(AlgorithmParam param);
}
