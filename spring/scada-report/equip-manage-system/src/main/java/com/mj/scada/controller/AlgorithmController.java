package com.mj.scada.controller;

import com.mj.scada.bean.json.ReturnData;
import com.mj.scada.log.MyLog;
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
@RequestMapping("algoModel")
public class AlgorithmController
{
    @Autowired
    private AlgorithmModelService algorithmModelService;
    @Autowired
    private AlgorithmNodeService algorithmNodeService;
    @Autowired
    private EquipDataService equipDataService;

    //返回算法模型列表
    @MyLog(value = "获取算法模型列表")
    @RequestMapping("list")
    public ReturnData modelList()
    {
        return algorithmModelService.modelList();
    }

    //增
    @MyLog(value = "添加算法模型")
    @PostMapping("model")
    public ReturnData addModel(@RequestBody String json)
    {
        return algorithmModelService.addModel(json);
    }

    //查询算法模型（模糊查询）
    @MyLog(value = "模糊查询算法模型")
    @RequestMapping("find")
    public ReturnData findModel(String name)
    {
        return algorithmModelService.fuzzyFindModel(name);
    }

    //算法模型信息
    @MyLog(value = "获取算法模型信息")
    @RequestMapping("info")
    public ReturnData modelInfo(Integer modelId)
    {
        return algorithmModelService.modelInfo(modelId);
    }

    //查看算法模型，进入修改算法模型界面
    @MyLog(value = "查看算法模型，进入修改算法模型界面")
    @RequestMapping("see")
    public ReturnData seeModel(Integer modelId)
    {
        return algorithmModelService.seeModel(modelId);
    }

    //删
    @MyLog(value = "删除算法模型")
    @PostMapping("del")
    public ReturnData deleteModel(Integer id)
    {
        return algorithmModelService.deleteModel(id);
    }

    //修改算法模型名称等信息
    @MyLog(value = "修改算法模型名称等信息")
    @PostMapping("update")
    public ReturnData updateModelInfo(String json)
    {
        return algorithmModelService.updateModelInfo(json);
    }

    //修改算法模型公式
    @MyLog(value = "修改算法模型公式")
    @PostMapping("furmula")
    public ReturnData updateFurmula(Integer id, String furmula)
    {
        return algorithmModelService.updateModelFurmula(id, furmula);
    }

    //关联数据(将数据相关信息传给前端，让前端进行筛选)
    @MyLog(value = "关联数据")
    @RequestMapping("link")
    public ReturnData linkData()
    {
        return equipDataService.linkData();
    }
}
