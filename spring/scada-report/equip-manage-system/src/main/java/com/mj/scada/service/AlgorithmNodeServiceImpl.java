package com.mj.scada.service;

import com.alibaba.fastjson.JSON;
import com.mj.scada.bean.domain.algorithm.AlgorithmNode;
import com.mj.scada.bean.domain.algorithm.AlgorithmParam;
import com.mj.scada.bean.json.algorithm.NodeAddJson;
import com.mj.scada.bean.json.algorithm.NodeInfoJson;
import com.mj.scada.bean.json.algorithm.NodeUpdateJson;
import com.mj.scada.bean.json.ReturnData;
import com.mj.scada.repository.AlgorithmNodeRepository;
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
 *Date:2019-04-02 10:20
 */
@Service
public class AlgorithmNodeServiceImpl implements AlgorithmNodeService
{
    @Autowired
    private AlgorithmNodeRepository algorithmNodeRepository;
    @Autowired
    private AlgorithmParamService algorithmParamService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private EquipDataService equipDataService;
    @Autowired
    private TimeTodayService timeTodayService;
    @Autowired
    private EquipService equipService;

    //增
    @Override
    @Transactional
    public ReturnData addNode(String json)
    {
        NodeAddJson nodeAddJson = JSON.parseObject(json, NodeAddJson.class);
        //同一modelId下，nodeName不能重复
        if (algorithmNodeRepository.findByModelIdAndNodeName(nodeAddJson.getModelId(), nodeAddJson.getNodeName()) != null)
        {
            return new ReturnData(1, "操作失败，节点名重复", null);
        }
        //存储算法元素
        for (AlgorithmParam p: nodeAddJson.getParams())
        {
            if (algorithmParamService.judgeAddName(p))
            {
                algorithmParamService.addParam(p);
            }
            else
            {
                return new ReturnData(1, "操作失败，元素名重复", null);
            }
        }

        //存储节点
        AlgorithmNode node = new AlgorithmNode(nodeAddJson.getModelId(), nodeAddJson.getNodeName(), nodeAddJson.getFurmula());
        algorithmNodeRepository.save(node);
        return new ReturnData(0, "操作成功", null);
    }

    //删
    @Override
    public ReturnData delNode(Integer nodeId)
    {
        //删除节点
        algorithmNodeRepository.delete(nodeId);

        //删除该节点下的所有算法元素
        algorithmParamService.deleteAllParam(nodeId);

        return new ReturnData(0, "操作成功", null);
    }

    //修改节点
    @Override
    @Transactional
    public ReturnData updateNode(String json)
    {
        NodeUpdateJson data=JSON.parseObject(json, NodeUpdateJson.class);

        if (algorithmParamService.judgeName(data.getAddParams(), data.getUpdateParams()))
        {
            //添加算法元素
            if (data.getAddParams()!=null)
            {
                for (AlgorithmParam p:data.getAddParams())
                {
                    ReturnData d=algorithmParamService.addParam(p);
                    if (d.getCode()==1) //添加失败
                    {
                        return d;
                    }
                }
            }

            //删除算法元素
            if (data.getDelParams()!=null)
            {
                for (Integer i:data.getDelParams())
                {
                    algorithmParamService.deleteParam(i);
                }
            }

            //修改算法元素
            if (data.getUpdateParams()!=null)
            {
                for (AlgorithmParam p:data.getUpdateParams())
                {
                    ReturnData d=algorithmParamService.updateParam(p);
                    if (d.getCode()==1) //修改失败
                    {
                        return d;
                    }
                }
            }

            //修改算法节点
            AlgorithmNode node=algorithmNodeRepository.findOne(data.getId());
            if (!node.getNodeName().equals(data.getNodeName())) //nodeName有修改
            {
                if (algorithmNodeRepository.findByModelIdAndNodeName(data.getModelId(), data.getNodeName())!=null)
                {
                    return new ReturnData(1, "操作失败，节点名字重复", null);
                }
                //名字有修改，且未重复
                node.setNodeName(data.getNodeName());
            }
            node.setFurmula(data.getFurmula());
            algorithmNodeRepository.save(node);
            return new ReturnData(0,"操作成功",null);
        }
        else
        {
            return new ReturnData(1,"操作失败，名字重复",null);
        }
    }

    //删除AlgorithmModel对应的所有节点
    @Override
    @Transactional
    public ReturnData deleteAllNode(Integer modelId)
    {
        List<AlgorithmNode> nodeList = algorithmNodeRepository.findByModelId(modelId);
        for (AlgorithmNode node : nodeList)
        {
            //删除所有node
            algorithmNodeRepository.delete(node);
            //删除所有param
            algorithmParamService.deleteAllParam(node.getId());
        }
        return new ReturnData(0, "操作成功", null);
    }

    @Override
    public ReturnData seeNode(Integer nodeId)
    {
        AlgorithmNode node = algorithmNodeRepository.findOne(nodeId);
        List<AlgorithmParam> list=algorithmParamService.findNodeParam(nodeId);
        NodeInfoJson nodeInfoJson=new NodeInfoJson(node, list);
        return new ReturnData(0,"操作成功",nodeInfoJson);
    }

    //通过公式计算得到对应的AlgorithmNode的值(所有设备的值或某类设备的值)
    @Override
    public Map<String, String> getValue(Integer nodeId, String type) throws ScriptException
    {
        Map<String, String> nodeValueMap = new HashMap<>();

        List<String> equipSortList = getEquipList(type);
        List<AlgorithmParam> paramList = algorithmParamService.findNodeParam(nodeId);
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");

        AlgorithmNode node = algorithmNodeRepository.findOne(nodeId);
        String furmula = node.getFurmula();
        for (String s : equipSortList)
        {
            for (AlgorithmParam param : paramList)
            {
                String connectType = param.getConnectType();
                String keyName = param.getConnectKeyName();
                String value = "";
                if (connectType.equals("导入类数据"))
                {
                    value = equipDataService.findByTypeAndKeyName(connectType, keyName).getValue();
                }
                else if (connectType.equals("通用类数据") && keyName.equals("startupTime"))
                {
                    //设备开机时间，实时计算
                    value = timeTodayService.getTodayStartupTime(s).toString();
                }
                else
                {
                    value = equipDataService.findByDeviceNumAndTypeAndKeyName(s, connectType, keyName).getValue();
                }
                engine.put(param.getVariable(), value);
            }
            String result = engine.eval(furmula).toString();
            nodeValueMap.put(s, result);
        }
        return nodeValueMap;
    }

    //根据AlgorithmModel的Id，获取其对应的所有AlgorithmNode
    @Override
    public List<AlgorithmNode> findAllByModelId(Integer modelId)
    {
        return algorithmNodeRepository.findByModelId(modelId);
    }

    /**
     * 获取设备列表
     *
     * @return 如果type=all，返回所有设备；如果type=设备类型，返回所有该类设备；
     * 返回的list中的元素是设备的deviceNum
     */
    private List<String> getEquipList(String type)
    {
        List<String> equipSortList = new ArrayList<>();
        if (type.equals("all")) //获取所有设备的值
        {
            for (Map.Entry<String, String> entry : equipService.getEquipNameMap().entrySet())
            {
                equipSortList.add(entry.getKey());
            }
        }
        else    //获取某类设备的值
        {
            Map<String,String> equipSortMap=equipService.getEquipSortMap();
            for (Map.Entry<String,String> entry:equipSortMap.entrySet())
            {
                if (entry.getValue().equals(type))
                {
                    equipSortList.add(entry.getKey());
                }
            }
        }
        return equipSortList;
    }

}
