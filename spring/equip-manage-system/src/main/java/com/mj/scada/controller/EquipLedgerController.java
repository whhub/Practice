package com.mj.scada.controller;


import com.mj.scada.bean.json.ReturnData;
import com.mj.scada.service.EquipLedgerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
 *Author:ZouHeng
 *Des:设备台账Controller
 *Date:2019-03-18 17:26
 */
@org.springframework.web.bind.annotation.RestController
@RequestMapping("ledger")
public class EquipLedgerController
{
    @Autowired
    private EquipLedgerService equipLedgerService;

    //设备台账首页
    @RequestMapping("home")
    public ReturnData home()
    {
        return equipLedgerService.home();
    }

    //增(该接口暂时废弃)
    @RequestMapping("add")
    public ReturnData add(@RequestBody String json)
    {
        return equipLedgerService.add(json);
    }

    //删(该接口暂时废弃)
    @DeleteMapping("del")
    public ReturnData delete(Integer id)
    {
        return equipLedgerService.delete(id);
    }

    //改
    @PutMapping("edit")
    public ReturnData editEquipLedger(@RequestBody String json)
    {
        return equipLedgerService.update(json);
    }

    //设备名模糊查询
    @RequestMapping("find")
    public ReturnData find(String status,@RequestParam(required = false) String name)
    {
        return equipLedgerService.find(status, name);
    }

    //根据id查询台账
    @RequestMapping("findById")
    public ReturnData findById(Integer id)
    {
        return equipLedgerService.findById(id);
    }

    //同步设备
    @PostMapping("sync")
    public ReturnData sync()
    {
        return equipLedgerService.updateEquipLedgerFromRest();
    }
}
