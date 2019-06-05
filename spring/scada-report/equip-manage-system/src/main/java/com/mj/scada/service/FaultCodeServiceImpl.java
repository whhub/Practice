package com.mj.scada.service;

import com.alibaba.fastjson.JSON;
import com.mj.scada.bean.domain.FaultCode;
import com.mj.scada.bean.json.ReturnData;
import com.mj.scada.repository.FaultCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-05-29 13:49
 */
@Service
public class FaultCodeServiceImpl implements FaultCodeService
{
    @Autowired
    private FaultCodeRepository faultCodeRepository;

    @Override
    @Transactional
    public ReturnData add(String json)
    {
        FaultCode faultCode= JSON.parseObject(json, FaultCode.class);
        faultCodeRepository.save(faultCode);
        return new ReturnData(0,"操作成功",null);
    }

    @Override
    @Transactional
    public ReturnData del(Integer id)
    {
        faultCodeRepository.delete(id);
        return new ReturnData(0,"操作成功",null);
    }

    @Override
    @Transactional
    public ReturnData update(String json)
    {
        FaultCode faultCode= JSON.parseObject(json, FaultCode.class);
        faultCodeRepository.save(faultCode);
        return new ReturnData(0,"操作成功",null);
    }

    @Override
    public ReturnData getList()
    {
        List<FaultCode> list = faultCodeRepository.findAll();
        return new ReturnData(0,"操作成功",list);
    }
}
