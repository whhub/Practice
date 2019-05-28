package com.mj.scada.controller;

import com.alibaba.fastjson.JSON;
import com.mj.scada.bean.domain.type.EquipBrand;
import com.mj.scada.bean.json.ReturnData;
import com.mj.scada.bean.json.TypeData;
import com.mj.scada.service.EquipBrandService;
import com.mj.scada.service.EquipTypeService;
import com.mj.scada.bean.domain.type.EquipType;
import org.hibernate.loader.custom.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/*
 *Author:ZouHeng
 *Des:设备类别Controller
 *Date:2019-03-27 11:01
 */
@RestController
@RequestMapping("type")
public class EquipTypeController
{
    @Autowired
    private EquipTypeService equipTypeService;
    @Autowired
    private EquipBrandService equipBrandService;

    //设备类别首页
    @RequestMapping("home")
    public ReturnData home()
    {
        return equipTypeService.home();
    }

    //新增设备类别
    @PostMapping("addType")
    public ReturnData addEequipType(@RequestBody String json)
    {
        return equipTypeService.addType(json);
    }

    //删除设备类别
    @DeleteMapping("delType")
    public ReturnData delType(Integer id)
    {
        return equipTypeService.delType(id);
    }

    //修改设备类别
    @PostMapping("updateType")
    public ReturnData updateEquipType(@RequestBody String json)
    {
        return equipTypeService.updateType(json);
    }

    //新增品牌
    @PostMapping("addBrand")
    public ReturnData addBrand(@RequestBody String json)
    {
//        System.out.println(json);
        return equipBrandService.addBrand(json);
    }

    //删除品牌
    @DeleteMapping("delBrand")
    public ReturnData delBrand(Integer id)
    {
        return equipBrandService.delBrand(id);
    }

    //修改品牌
    @PutMapping("updateBrand")
    public ReturnData updateBrand(@RequestBody String json)
    {
        return equipBrandService.updateBrand(json);
    }

    //得到所有设备类别的名称
    @RequestMapping("getTypeNames")
    public ReturnData findAllEquipTypeName()
    {
        return equipTypeService.findAllTypeName();
    }

    //模糊查询设备类别名称
    @RequestMapping("fuzzyTypeName")
    public ReturnData fuzzyTypeName(String name)
    {
        return equipTypeService.fuzzyTypeName(name);
    }

    //查询
    @RequestMapping("find")
    public ReturnData find(String typeName,String status)
    {
        return equipTypeService.find(typeName, status);
    }
}
