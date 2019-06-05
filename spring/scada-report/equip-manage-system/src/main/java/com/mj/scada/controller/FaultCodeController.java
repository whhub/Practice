package com.mj.scada.controller;

import com.mj.scada.bean.json.ReturnData;
import com.mj.scada.log.MyLog;
import com.mj.scada.service.FaultCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-05-29 13:47
 */
@RestController
@RequestMapping("faultCode")
public class FaultCodeController
{
    @Autowired
    private FaultCodeService faultCodeService;

    @MyLog(value = "添加故障码")
    @PostMapping("add")
    public ReturnData add(@RequestBody String json)
    {
        return faultCodeService.add(json);
    }

    @MyLog(value = "删除故障码")
    @DeleteMapping("del")
    public ReturnData del(Integer id)
    {
        return faultCodeService.del(id);
    }

    @MyLog(value = "修改故障码")
    @PutMapping("update")
    public ReturnData update(@RequestBody String json)
    {
        return faultCodeService.update(json);
    }

    @MyLog(value = "获取故障码列表")
    @RequestMapping("list")
    public ReturnData getList()
    {
        return faultCodeService.getList();
    }
}
