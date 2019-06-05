package com.mj.scada.service;

import com.alibaba.fastjson.JSON;
import com.mj.scada.bean.domain.type.EquipBrand;
import com.mj.scada.bean.domain.type.EquipType;
import com.mj.scada.bean.json.ReturnData;
import com.mj.scada.bean.json.TypeData;
import com.mj.scada.bean.json.TypeTreeJson;
import com.mj.scada.repository.EquipTypeRepository;
import com.mj.scada.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.BinaryClient;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-27 10:56
 */
@Service
public class EquipTypeServiceImpl implements EquipTypeService
{
    @Autowired
    private EquipTypeRepository equipTypeRepository;
    @Autowired
    private EquipBrandService equipBrandService;

    //修改设备类别
    @Override
    @Transactional
    public ReturnData updateType(String json)
    {
        EquipType equipType = JSON.parseObject(json, EquipType.class);
        equipType.setUpdateTime(Util.getCurrentDateAndTime());
        equipTypeRepository.save(equipType);
        return new ReturnData(0,"操作成功",null);
    }

    //设备类别首页
    @Override
    public ReturnData home()
    {
        List<EquipType> types=equipTypeRepository.findAll();
        List<EquipBrand> brands=equipBrandService.findAll();
        TypeData data=new TypeData(types, brands);
        return new ReturnData(0,"操作成功",data);
    }

    //新增设备类别
    @Override
    @Transactional
    public ReturnData addType(String json)
    {
        EquipType equipType = JSON.parseObject(json, EquipType.class);
        equipType.setUpdateTime(Util.getCurrentDateAndTime());
        equipTypeRepository.save(equipType);
        return new ReturnData(0,"操作成功",null);
    }

    //删除设备类别
    @Override
    @Transactional
    public ReturnData delType(Integer id)
    {
        equipTypeRepository.delete(id);

        //删除该类别下的所有品牌
        equipBrandService.deleteAll(id);

        return new ReturnData(0,"操作成功",null);
    }

    /**
     * 得到所有设备类别的id和名称
     * @return  Integer：类别id；String：类别名称
     */
    @Override
    public ReturnData findAllTypeName()
    {
        List<EquipType> equipTypeList=equipTypeRepository.findAll();
        Map<Integer,String> map = new HashMap<>();
        for (EquipType type:equipTypeList)
        {
            map.put(type.getId(), type.getTypeName());
        }
        return new ReturnData(0,"操作成功",map);
    }

    //模糊查询
    @Override
    public ReturnData fuzzyTypeName(String name)
    {
        List<EquipType> types=equipTypeRepository.fuzzyByName(name);
        Map<Integer,String> map = new HashMap<>();
        for (EquipType t:types)
        {
            map.put(t.getId(), t.getTypeName());
        }
        return new ReturnData(0,"操作成功",map);
    }

    //首页上的查询
    @Override
    public ReturnData find(String typeName, String status)
    {
        if (typeName.equals("全部"))
        {
            List<EquipType> types=equipTypeRepository.findByStatus(status);
            List<EquipBrand> brands=equipBrandService.find(types);
            TypeData data=new TypeData(types, brands);
            return new ReturnData(0,"操作成功",data);
        }
        else
        {
            List<EquipType> types = equipTypeRepository.findByTypeNameAndStatus(typeName, status);
            List<EquipBrand> brands=equipBrandService.find(types);
            TypeData data=new TypeData(types, brands);
            return new ReturnData(0,"操作成功",data);
        }
    }

    //返回所有设备种类
    @Override
    public ReturnData findAll()
    {
        List<EquipType> list=equipTypeRepository.findAll();
        return new ReturnData(0,"操作成功",list);
    }

    //返回类别品牌树
    @Override
    public ReturnData tree()
    {
        List<TypeTreeJson> list =new ArrayList<>();
        List<EquipType> types=equipTypeRepository.findAll();
        for (EquipType type:types)
        {
            List<EquipBrand> brands=equipBrandService.findByTypeId(type.getId());
            TypeTreeJson typeTreeJson=new TypeTreeJson(type, brands);
            list.add(typeTreeJson);
        }
        return new ReturnData(0,"操作成功",list);
    }

}
