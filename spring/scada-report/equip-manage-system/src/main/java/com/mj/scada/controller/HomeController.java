package com.mj.scada.controller;

import com.mj.scada.bean.json.ReturnData;
import com.mj.scada.common.AuthConst;
import com.mj.scada.log.MyLog;
import com.mj.scada.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.AbstractDocument;

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
    @MyLog(value = "获取设备运行状态监控")
    @RequestMapping("status")
    public ReturnData status()
    {
        return homeService.status();
    }

    //设备类别比例
    @MyLog(value = "获取设备类别比例")
    @RequestMapping("types")
    public ReturnData types()
    {
        return homeService.types();
    }

    //设备状态监控详情
    @MyLog(value = "获取设备状态监控详情")
    @RequestMapping("statusInfo")
    public ReturnData statusInfo()
    {
        return homeService.statusInfo();
    }

    //设备种类开关机百分比
    @MyLog(value = "获取设备种类开关机百分比")
    @RequestMapping("startup")
    public ReturnData startup()
    {
        return homeService.startup();
    }

    //test,获取session
//    @RequestMapping("session")
//    public String test(HttpServletRequest request)
//    {
////        AbstractDocument.Content
////        ResourceProperties.Content.log
//
//        System.out.println(request.getSession().getAttribute(AuthConst.LOGIN_USER));
//        return "succ";
//    }
}
