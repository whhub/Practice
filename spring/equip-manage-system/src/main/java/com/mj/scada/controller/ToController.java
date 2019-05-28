package com.mj.scada.controller;

import com.alibaba.fastjson.JSON;
import com.mj.scada.bean.domain.MaintainEvent;
import com.mj.scada.service.EquipLedgerService;
import com.mj.scada.service.MaintainEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/*
 *Author:ZouHeng
 *Des:页面跳转Controller
 *Date:2019-03-18 15:08
 */
@Controller
public class ToController
{
    @Autowired
    private EquipLedgerService equipInfoService;
    @Autowired
    private MaintainEventService maintainInfoServer;

    //台账
    @RequestMapping("ledger")
    public String ledger(Model model)
    {
//        equipInfoService.getEquipInfoAndSave();
//        List<EquipLedger> list = equipInfoService.findAll();
//        String json= JSON.toJSONString(list);
//        model.addAttribute("equipInfos", json);
        return "index";
    }

    //维护
    @RequestMapping("maintain")
    public String maintain(Model model)
    {
        List<MaintainEvent> maintainEventList = maintainInfoServer.findAll();
        String maintainInfoJson=JSON.toJSONString(maintainEventList);
        model.addAttribute("maintainInfo", maintainInfoJson);
        return "maintain";
    }
}
