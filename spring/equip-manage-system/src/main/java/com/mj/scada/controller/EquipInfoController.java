package com.mj.scada.controller;

import com.mj.scada.bean.json.ReturnData;
import com.mj.scada.service.DocService;
import com.mj.scada.service.EquipBrandService;
import com.mj.scada.service.EquipInfoService;
import com.mj.scada.service.EquipTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/*
 *Author:ZouHeng
 *Des:设备详情Controller
 *Date:2019-03-26 10:09
 */
@RestController
@RequestMapping("info")
public class EquipInfoController
{
    @Autowired
    private EquipInfoService equipInfoService;
    @Autowired
    private EquipTypeService equipTypeService;
    @Autowired
    private EquipBrandService equipBrandService;
    @Autowired
    private DocService docService;

    //设备详情home界面
    @RequestMapping("home")
    public ReturnData home()
    {
        return equipInfoService.home();
    }

    //显示设备的总览信息
    @RequestMapping("pandect")
    public ReturnData pandect(String deviceNum)
    {
        return equipInfoService.pandect(deviceNum);
    }

    //得到设备所有档案的相关信息
    @RequestMapping("docs")
    public ReturnData getEquipDoc(String deviceNum)
    {
        return equipInfoService.getEquipDoc(deviceNum);
    }

    //下载设备档案
    @RequestMapping("download")
    public ReturnData downloadMinioFile(String docName, HttpServletResponse response)
    {
        return equipInfoService.downFile(docName, response);
    }

    //上传设备图片
    @PostMapping("uploadImg")
    public ReturnData uploadImg(MultipartFile file,String deviceNum)
    {
        return equipInfoService.uploadImg(file, deviceNum);
    }

    //获取所有的设备类别
    @RequestMapping("equipType")
    public ReturnData equipType()
    {
        return equipTypeService.findAll();
    }

    //获取某类设备的所有品牌
    @RequestMapping("equipBrand")
    public ReturnData equipBrand(Integer typeId)
    {
        return equipBrandService.findByTypeId(typeId);
    }

    //根据设备种类和品牌返回对应的文档
    @RequestMapping("docByTAndB")
    public ReturnData docByTAndB(String type, String classify)
    {
        return docService.findByTypeAndClassify(type, classify);
    }

    //关联文档
    @PostMapping("connect")
    public ReturnData connect(String deviceNum,Integer docId)
    {
        return equipInfoService.connect(deviceNum,docId);
    }

    //删除文档关联
    @DeleteMapping("disconnect")
    public ReturnData disConnect(String deviceNum,Integer docId)
    {
        return equipInfoService.disconnect(deviceNum, docId);
    }

    //获取设备实时信号
    @RequestMapping("realtimeSignal")
    public ReturnData getRealTimesiganl(String deviceNum)
    {
        return equipInfoService.getRealTimeSignal(deviceNum);
    }
}
