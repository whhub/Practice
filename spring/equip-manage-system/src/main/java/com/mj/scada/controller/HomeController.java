package com.mj.scada.controller;

import com.mj.scada.bean.json.ReturnData;
import com.mj.scada.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-05-23 10:52
 */
@RestController
@RequestMapping("home")
public class HomeController
{
    @Autowired
    private HomeService homeService;

    //设备运行状态监控
    @RequestMapping("status")
    public ReturnData status()
    {
        return homeService.status();
    }

    //设备类别比例
    @RequestMapping("types")
    public ReturnData types()
    {
        return homeService.types();
    }

    //设备状态监控详情
    @RequestMapping("statusInfo")
    public ReturnData statusInfo()
    {
        return new ReturnData(0,"操作成功",null);
//        return homeService.statusInfo();
    }

    //设备种类开关机百分比

}
