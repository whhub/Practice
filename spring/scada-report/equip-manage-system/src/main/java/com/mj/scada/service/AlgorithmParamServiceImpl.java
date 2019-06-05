package com.mj.scada.service;

import com.mj.scada.bean.domain.algorithm.AlgorithmParam;
import com.mj.scada.bean.json.ReturnData;
import com.mj.scada.repository.AlgorithmParamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-04-02 10:20
 */
@Service
public class AlgorithmParamServiceImpl implements AlgorithmParamService
{
    @Autowired
    private AlgorithmParamRepository algorithmParamRepository;
    @Autowired
    private EquipDataService equipDataService;

    //增
    @Override
    @Transactional
    public ReturnData addParam(AlgorithmParam param)
    {
        //相同的nodeId下的paramName不能重复
        if (!judgeAddName(param))
        {
            //名称重复
            return new ReturnData(1,"操作失败，算法元素名称重复！",null);
        }
        algorithmParamRepository.save(param);
        return new ReturnData(0,"操作成功",null);
    }

    //删除AlgorithmNode对应的所有元素
    @Override
    @Transactional
    public void deleteAllParam(Integer nodeId)
    {
        List<AlgorithmParam> paramList = algorithmParamRepository.findByNodeId(nodeId);
        for (AlgorithmParam p : paramList)
        {
            algorithmParamRepository.delete(p);
        }
    }

    //删除算法元素
    @Override
    @Transactional
    public ReturnData deleteParam(Integer paramId)
    {
        algorithmParamRepository.delete(paramId);
        return new ReturnData(0,"操作成功",null);
    }

    //修改算法元素
    @Override
    @Transactional
    public ReturnData updateParam(AlgorithmParam param)
    {
        if (!judgeUpdateName(param))
        {
            return new ReturnData(1,"操作失败，算法元素名字重复",null);
        }
        AlgorithmParam algorithmParam = algorithmParamRepository.findOne(param.getId());
        algorithmParam.setParamName(param.getParamName());
        algorithmParam.setVariable(param.getVariable());
        algorithmParam.setConnectType(param.getConnectType());
        algorithmParam.setConnectKeyName(param.getConnectKeyName());

        algorithmParamRepository.save(algorithmParam);
        return new ReturnData(0,"操作成功",null);
    }

    /**
     * 名字判断
     * @return true:可以进行添加和修改操作，名字未重复；  false：不能进行添加和修改操作，名字重复；
     */
    @Override
    public Boolean judgeName(List<AlgorithmParam> addParams, List<AlgorithmParam> updateParams)
    {
        for (AlgorithmParam p:addParams)
        {
            if (!judgeAddName(p))
            {
                return false;
            }
        }
        for (AlgorithmParam p:updateParams)
        {
            if (!judgeUpdateName(p))
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<AlgorithmParam> findNodeParam(Integer nodeId)
    {
        return algorithmParamRepository.findByNodeId(nodeId);
    }


    /**
     * 判断添加元素名字是否重复
     * @return false:名字重复，不可以添加；true：名字未重复，可以添加；
     */
    @Override
    public Boolean judgeAddName(AlgorithmParam param)
    {
        if (algorithmParamRepository.findByNodeIdAndParamName(param.getNodeId(), param.getParamName())!=null)
        {
            //名称重复
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * 判断修改元素名字是否重复
     * @return false:名字重复，不可以添加；true：名字未重复，可以添加；
     */
    @Override
    public Boolean judgeUpdateName(AlgorithmParam param)
    {
        AlgorithmParam algorithmParam = algorithmParamRepository.findOne(param.getId());
        if (!param.getParamName().equals(algorithmParam.getParamName()))    //名字有修改
        {
            if (algorithmParamRepository.findByNodeIdAndParamName(param.getNodeId(), param.getParamName())!=null)
            {
                return false;
            }
        }
        return true;
    }

}
