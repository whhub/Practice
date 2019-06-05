package com.mj.scada.service;

import com.alibaba.fastjson.JSON;
import com.mj.scada.bean.domain.algorithm.AlgorithmModel;
import com.mj.scada.bean.domain.algorithm.AlgorithmNode;
import com.mj.scada.bean.domain.algorithm.AlgorithmParam;
import com.mj.scada.bean.json.ReturnData;
import com.mj.scada.bean.json.algorithm.ModelJson;
import com.mj.scada.bean.json.algorithm.NodeInfoJson;
import com.mj.scada.repository.AlgorithmModelRepository;
import com.mj.scada.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-04-01 16:18
 */
@Service
public class AlgorithmModelServiceImpl implements AlgorithmModelService
{
    @Autowired
    private AlgorithmModelRepository algorithmModelRepository;
    @Autowired
    private AlgorithmNodeService algorithmNodeService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private AlgorithmParamService algorithmParamService;
    @Autowired
    private EquipService equipService;

    //算法模型列表
    @Override
    public ReturnData modelList()
    {
        List<AlgorithmModel> list=algorithmModelRepository.findAll();
        return new ReturnData(0,"操作成功",list);
    }

    //添加算法模型
    @Override
    @Transactional
    public ReturnData addModel(String json)
    {
        AlgorithmModel model1=JSON.parseObject(json, AlgorithmModel.class);
        //判断名字是否重复
        if (algorithmModelRepository.findByModelName(model1.getModelName())!=null)
        {
            return new ReturnData(1,"操作失败，算法模型名称重复！",null);
        }
        AlgorithmModel model=new AlgorithmModel(model1.getModelName(),model1.getVersion(),model1.getIsOpen(),Util.getCurrentDateAndTime(),model1.getMsg(),model1.getAuthor(),model1.getFurmula(),0,1);
        algorithmModelRepository.save(model);
        return new ReturnData(0,"操作成功",null);
    }

    //模糊查询
    @Override
    public ReturnData fuzzyFindModel(String name)
    {
        AlgorithmModel model=algorithmModelRepository.fuzzyFindModel(name);
        return new ReturnData(0,"操作成功",model);
    }

    //查看算法模型
    @Override
    public ReturnData modelInfo(Integer modelId)
    {
        AlgorithmModel model = algorithmModelRepository.findOne(modelId);
        return new ReturnData(0,"操作成功",model);
    }

    //删
    @Override
    @Transactional
    public ReturnData deleteModel(Integer id)
    {
        AlgorithmModel model=algorithmModelRepository.findOne(id);
        algorithmModelRepository.delete(id);
        //删除该model对应的所有AlgorithmNode
        algorithmNodeService.deleteAllNode(model.getId());
        return new ReturnData(0,"操作成功",null);
    }

    //修改算法模型信息
    @Override
    @Transactional
    public ReturnData updateModelInfo(String json)
    {
        AlgorithmModel model1=JSON.parseObject(json, AlgorithmModel.class);
        AlgorithmModel model=algorithmModelRepository.findOne(model1.getId());
        //修改算法模型名称（判断名称是否重复）
        if (!model.getModelName().equals(model1.getModelName()))
        {
            if (algorithmModelRepository.findByModelName(model1.getModelName())!=null)
            {
                //名字重复
                return new ReturnData(1,"操作失败，算法模型名称重复！",null);
            }
        }
        //修改其他内容
        model.setModelName(model1.getModelName());
        model.setVersion(model1.getVersion());
        model.setIsOpen(model1.getIsOpen());
        model.setUpdateDate(Util.getCurrentDateAndTime());
        model.setMsg(model1.getMsg());
        model.setAuthor(model1.getAuthor());
        model.setFurmula(model1.getFurmula());
        //动态+1
        Integer num=model.getDynamic();
        num+=1;
        model.setDynamic(num);
        algorithmModelRepository.save(model);
        return new ReturnData(0,"操作成功",null);
    }

    //修改算法模型计算公式
    @Override
    public ReturnData updateModelFurmula(Integer id, String furmula)
    {
        AlgorithmModel model=algorithmModelRepository.findOne(id);
        model.setFurmula(furmula);
        //动态+1
        Integer num=model.getDynamic();
        num+=1;
        model.setDynamic(num);
        algorithmModelRepository.save(model);

        return new ReturnData(0,"操作成功",null);
    }

    @Override
    public ReturnData seeModel(Integer modelId)
    {
        AlgorithmModel model=algorithmModelRepository.findOne(modelId);
        List<AlgorithmNode> algorithmNodes=algorithmNodeService.findAllByModelId(modelId);
        List<NodeInfoJson> nodes=new ArrayList<>();
        for (AlgorithmNode node:algorithmNodes)
        {
            nodes.add((NodeInfoJson) algorithmNodeService.seeNode(node.getId()).getData());
        }
        ModelJson modelJson=new ModelJson(model,nodes);
        return new ReturnData(0,"操作成功",modelJson);
    }

    //根据modelName发现对应的AlgorithmModel（注意：ModelName不会重复）
    @Override
    public AlgorithmModel findByName(String name)
    {
        return algorithmModelRepository.findByModelName(name);
    }

    @Override
    public AlgorithmModel findById(Integer modelId)
    {
        return algorithmModelRepository.findOne(modelId);
    }

    /**
     * 算法模型通用，计算所有设备的该算法模型的值
     * @return
     */
    @Override
    public Map<String,String> calculateAll(String modelName) throws ScriptException
    {
        return calculateSort(modelName, "all");
    }

    /**
     * 计算单类设备的该算法模型的值
     * @param modelName 算法模型的名称
     * @param TypeName  设备类型
     * @return <deviceNum,value>
     */
    @Override
    public Map<String, String> calculateSort(String modelName,String TypeName) throws ScriptException
    {
        Map<String,String> modelValueMap=new HashMap<>();
        AlgorithmModel model=findByName(modelName);
        String furmula=model.getFurmula();  //算法模型的计算公式
        List<AlgorithmNode> nodeList=algorithmNodeService.findAllByModelId(model.getId());
        //<deviceNum,deviceName>

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        for (Map.Entry<String,String> entry:equipService.getEquipNameMap().entrySet())
        {
            for (AlgorithmNode node:nodeList)
            {
                Map<String,String> nodeValueMap=algorithmNodeService.getValue(node.getId(), TypeName);
                String value="";
                for (Map.Entry<String,String> nodeValue:nodeValueMap.entrySet())
                {
                    if (nodeValue.getKey().equals(entry.getKey()))
                    {
                        value=nodeValue.getValue();
                    }
                }
                engine.put(node.getNodeName(), value);
            }
            String result = engine.eval(furmula).toString();
            modelValueMap.put(entry.getKey(), result);
        }
        return modelValueMap;
    }


}
