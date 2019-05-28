package com.mj.scada.controller;

import com.alibaba.fastjson.JSON;
import com.mj.scada.bean.domain.statistics.StaTable;
import com.mj.scada.bean.json.ReturnData;
import com.mj.scada.bean.domain.statistics.StatisticTableParam;
import com.mj.scada.bean.json.SeeTableData;
import com.mj.scada.bean.json.TableDetail;
import com.mj.scada.service.StaTableParamService;
import com.mj.scada.service.StaTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.script.ScriptException;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-04-29 11:37
 */
@RestController
@RequestMapping("table")
public class StaTableController
{
    @Autowired
    private StaTableService staTableService;

    //添加报表
    @PostMapping("add")
    public ReturnData addTable(String tableName,String remarks)
    {
        return staTableService.addStaTable(tableName,remarks);
    }

    //查询表（模糊查询）
    @RequestMapping("find")
    public ReturnData findTable(String tableName)
    {
        return staTableService.findTable(tableName);
    }

    //报表列表
    @RequestMapping("list")
    public ReturnData getAllTable()
    {
        return staTableService.getTableList();
    }

    //查看统计表详情，返回统计表的所有元素(进入统计表修改页面)
    @RequestMapping("detail")
    public ReturnData getTableDetail(Integer id)
    {
        return staTableService.getTableDetail(id);
    }

    //删除表
    @DeleteMapping("del")
    public ReturnData deleteTable(Integer id)
    {
        return staTableService.deleteTable(id);
    }

    //更新表（修改名字或备注，同时修改表元素）,该接口包含配置构建表功能
    @PutMapping("update")
    public ReturnData updateTable(String json)
    {
        return staTableService.updateTable(json);
    }

    //查看统计表数据详情
    @RequestMapping("data")
    public ReturnData seeTable(Integer id) throws ScriptException
    {
        return staTableService.seeTable(id);
    }

    //导出报表
    @PostMapping("export")
    public ReturnData exportTable(Integer id, HttpServletResponse response) throws Exception
    {
        return staTableService.exportTable(id,response);
    }

    //关联数据
    @PostMapping("linkData")
    public String union()
    {
        //TODO:逻辑没写
        return null;
    }
}
