package com.mj.scada.controller;

import com.mj.scada.service.EquipDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-04-09 11:29
 */
@RestController
@RequestMapping("equipData")
public class EquipDataController
{
    @Autowired
    private EquipDataService equipDataService;

    //存储用户输入数据
    @PostMapping("saveInputData")
    public String saveInputData(@RequestBody String json)
    {
        System.out.println(json);
        return equipDataService.saveInputData(json);
    }

    @RequestMapping("findDataByType")
    public String findDataByType(String type)
    {
        return equipDataService.findDataByType(type);
    }
}
