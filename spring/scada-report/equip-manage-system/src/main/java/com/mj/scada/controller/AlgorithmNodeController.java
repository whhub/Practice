package com.mj.scada.controller;

import com.mj.scada.bean.json.ReturnData;
import com.mj.scada.log.MyLog;
import com.mj.scada.service.AlgorithmNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-05-31 16:14
 */
@RestController
@RequestMapping("algoNode")
public class AlgorithmNodeController
{
    @Autowired
    private AlgorithmNodeService algorithmNodeService;

    //添加算法节点
    @MyLog(value = "添加算法节点")
    @PostMapping("add")
    public ReturnData addNode(@RequestBody String json)
    {
        return algorithmNodeService.addNode(json);
    }

    //修改算法节点
    @MyLog(value = "修改算法节点")
    @PutMapping("update")
    public ReturnData updateNode(@RequestBody String json)
    {
        return algorithmNodeService.updateNode(json);
    }

    //查看算法节点信息
    @MyLog(value = "查看算法节点信息")
    @RequestMapping("see")
    public ReturnData seeNode(Integer nodeId)
    {
        return algorithmNodeService.seeNode(nodeId);
    }

    //删除算法节点
    @MyLog(value = "删除算法节点")
    @DeleteMapping("del")
    public ReturnData delNode(Integer nodeId)
    {
        return algorithmNodeService.delNode(nodeId);
    }
}
