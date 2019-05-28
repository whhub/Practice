package com.mj.scada.service;

import com.mj.scada.bean.domain.algorithm.AlgorithmModel;
import com.mj.scada.bean.json.ReturnData;

import javax.script.ScriptException;
import java.util.List;
import java.util.Map;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-04-01 16:18
 */
public interface AlgorithmModelService
{
    ReturnData modelList();
    ReturnData addModel(String json);
    ReturnData fuzzyFindModel(String name);
    ReturnData modelInfo(Integer modelId);
    ReturnData deleteModel(Integer id);
    ReturnData updateModelInfo(String json);
    ReturnData updateModelFurmula(Integer id, String furmula);
    ReturnData seeModel(Integer modelId);
    AlgorithmModel findByName(String name);
    AlgorithmModel findById(Integer modelId);
    Map<String,String> calculateAll(String modelName) throws ScriptException;
    Map<String,String> calculateSort(String modelName,String TypeName) throws ScriptException;
}
