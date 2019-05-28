package com.mj.scada.controller;

import com.mj.scada.bean.domain.doc.Doc;
import com.mj.scada.bean.domain.type.EquipBrand;
import com.mj.scada.bean.domain.type.EquipType;
import com.mj.scada.bean.json.DocDetail;
import com.mj.scada.bean.json.ReturnData;
import com.mj.scada.service.DocService;
import com.mj.scada.service.EquipBrandService;
import com.mj.scada.service.EquipTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/*
 *Author:ZouHeng
 *Des:档案管理
 *Date:2019-05-17 13:57
 */
@RestController
@RequestMapping("doc")
public class DocController
{
    @Autowired
    private DocService docService;
    @Autowired
    private EquipTypeService equipTypeService;
    @Autowired
    private EquipBrandService equipBrandService;

    @RequestMapping("list")
    public ReturnData list()
    {
        return docService.findAll();
    }

    @RequestMapping("find")
    public ReturnData fuzzyFind(String docName)
    {
        return docService.fuzzyFind(docName);
    }

    //删
    @DeleteMapping("del")
    public ReturnData del(Integer docId)
    {
        return docService.delDoc(docId);
    }

    //改
    @PutMapping("update")
    public ReturnData update(@RequestBody String json)
    {
        return docService.update(json);
    }

    @RequestMapping("detail")
    public ReturnData detail(Integer docId)
    {
        return docService.detail(docId);
    }

    //增
    @PostMapping("add")
    public ReturnData add(MultipartFile file, String docName, String type, String classify, String author, String introduction)
    {
        return docService.add(file, docName,type,classify,author,introduction);
    }

    //获取所有的设备类别
    @RequestMapping("equipTypes")
    public ReturnData equipTypes()
    {
        return equipTypeService.findAll();
    }

    //获取某类设备的所有品牌
    @RequestMapping("equipBrands")
    public ReturnData equipBrands(Integer typeId)
    {
        return equipBrandService.findByTypeId(typeId);
    }
}
