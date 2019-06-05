package com.mj.scada.service;

import com.alibaba.fastjson.JSON;
import com.mj.scada.bean.domain.type.EquipBrand;
import com.mj.scada.bean.domain.type.EquipType;
import com.mj.scada.bean.json.ReturnData;
import com.mj.scada.repository.EquipBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.BinaryClient;

import java.util.ArrayList;
import java.util.List;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-27 10:57
 */
@Service
public class EquipBrandServiceImpl implements EquipBrandService
{
    @Autowired
    private EquipBrandRepository equipBrandRepository;
    @Autowired
    private EquipTypeService equipTypeService;

    //新增品牌
    @Override
    @Transactional
    public ReturnData addBrand(String json)
    {
        EquipBrand brand= JSON.parseObject(json, EquipBrand.class);
        equipBrandRepository.save(brand);
        return new ReturnData(0,"操作成功",null);
    }

    //删除品牌
    @Override
    @Transactional
    public ReturnData delBrand(Integer id)
    {
        equipBrandRepository.delete(id);
        return new ReturnData(0,"操作成功",null);
    }

    //删除类别下的所有品牌
    @Override
    public void deleteAll(Integer typeId)
    {
        List<EquipBrand> list=equipBrandRepository.findByTypeId(typeId);
        for (EquipBrand b:list)
        {
            equipBrandRepository.delete(b);
        }
    }

    @Override
    public List<EquipBrand> findAll()
    {
        return equipBrandRepository.findAll();
    }

    //修改品牌
    @Override
    @Transactional
    public ReturnData updateBrand(String json)
    {
        EquipBrand brand=JSON.parseObject(json, EquipBrand.class);
        equipBrandRepository.save(brand);
        return new ReturnData(0,"操作成功",null);
    }

    @Override
    public List<EquipBrand> find(List<EquipType> types)
    {
        List<EquipBrand> brands = new ArrayList<>();
        for (EquipType t:types)
        {
            List<EquipBrand> list=equipBrandRepository.findByTypeId(t.getId());
            for (EquipBrand b:list)
            {
                brands.add(b);
            }
        }
        return brands;
    }

    //返回某类型下的所有品牌
    @Override
    public List<EquipBrand> findByTypeId(Integer typeId)
    {
        return equipBrandRepository.findByTypeId(typeId);
    }
}
