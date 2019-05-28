package com.mj.scada.controller;

import com.mj.scada.bean.json.ReturnData;
import com.mj.scada.service.AlgorithmModelService;
import com.mj.scada.service.AlgorithmNodeService;
import com.mj.scada.service.AlgorithmParamService;
import com.mj.scada.service.EquipDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-04-01 16:25
 */
@RestController
@RequestMapping("algorithm")
public class AlgorithmController
{
    @Autowired
    private AlgorithmModelService algorithmModelService;
    @Autowired
    private AlgorithmNodeService algorithmNodeService;
    @Autowired
    private EquipDataService equipDataService;

    //返回算法模型列表
    @RequestMapping("modelList")
    public ReturnData modelList()
    {
        return algorithmModelService.modelList();
    }

    //增
    @PostMapping("addModel")
    public ReturnData addModel(@RequestBody String json)
    {
        return algorithmModelService.addModel(json);
    }

    //查询算法模型（模糊查询）
    @RequestMapping("findModel")
    public ReturnData findModel(String name)
    {
        return algorithmModelService.fuzzyFindModel(name);
    }

    //算法模型信息
    @RequestMapping("modelInfo")
    public ReturnData modelInfo(Integer modelId)
    {
        return algorithmModelService.modelInfo(modelId);
    }

    //查看算法模型，进入修改算法模型界面
    @RequestMapping("seeModel")
    public ReturnData seeModel(Integer modelId)
    {
        return algorithmModelService.seeModel(modelId);
    }

    //删
    @PostMapping("delModel")
    public ReturnData deleteModel(Integer id)
    {
        return algorithmModelService.deleteModel(id);
    }

    //修改算法模型名称等信息
    @PostMapping("updateModel")
    public ReturnData updateModelInfo(String json)
    {
        return algorithmModelService.updateModelInfo(json);
    }

    //修改算法模型公式
    @PostMapping("updateFurmula")
    public ReturnData updateFurmula(Integer id, String furmula)
    {
        return algorithmModelService.updateModelFurmula(id, furmula);
    }

    //添加算法节点
    @PostMapping("addNode")
    public ReturnData addNode(@RequestBody String json)
    {
        return algorithmNodeService.addNode(json);
    }

    //修改算法节点
    @PutMapping("updateNode")
    public ReturnData updateNode(@RequestBody String json)
    {
        return algorithmNodeService.updateNode(json);
    }

    //查看算法节点信息
    @RequestMapping("seeNode")
    public ReturnData seeNode(Integer nodeId)
    {
        return algorithmNodeService.seeNode(nodeId);
    }

    //删除算法节点
    @DeleteMapping("delNode")
    public ReturnData delNode(Integer nodeId)
    {
        return algorithmNodeService.delNode(nodeId);
    }

    //关联数据(将数据相关信息传给前端，让前端进行筛选)
    @RequestMapping("linkData")
    public ReturnData linkData()
    {
        return equipDataService.linkData();
    }
}
