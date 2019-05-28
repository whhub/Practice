package com.mj.scada.service;

import com.alibaba.fastjson.JSON;
import com.mj.scada.bean.domain.statistics.StatisticTableParam;
import com.mj.scada.bean.json.ReturnData;
import com.mj.scada.bean.domain.statistics.StaTable;
import com.mj.scada.bean.json.SeeTableData;
import com.mj.scada.bean.json.TableDetail;
import com.mj.scada.bean.json.UpdateTableData;
import com.mj.scada.repository.StaTableRepository;
import com.mj.scada.util.ExcelUtil;
import com.mj.scada.util.Util;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.script.ScriptException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-04-30 14:55
 */
@Service
public class StaTableServiceImpl implements StaTableService
{
    @Autowired
    private StaTableRepository staTableRepository;
    @Autowired
    private StaTableParamService staTableParamService;
    @Autowired
    private RestTemplate restTemplate;

    //添加表，表名不能重复
    @Override
    @Transactional
    public ReturnData addStaTable(String tableName,String remarks)
    {
        if (staTableRepository.findByTableName(tableName)!=null)
        {
            return new ReturnData(1,"操作失败","表名重复");
        }
        else
        {
            //存储表
            StaTable table=new StaTable(tableName,Util.getCurrentDateAndTime(),0,remarks);
            staTableRepository.save(table);

            return new ReturnData(0,"操作成功",null);
        }
    }

    //删除表
    @Override
    @Transactional
    public ReturnData deleteTable(Integer id)
    {
        String tableName=staTableRepository.findOne(id).getTableName();
        staTableRepository.delete(id);

        //删除表中的元素
        staTableParamService.delTableParams(tableName);

        return new ReturnData(0,"操作成功",null);
    }

    //更新表（修改名字或备注，同时修改表元素）
    @Override
    @Transactional
    public ReturnData updateTable(String json)
    {
        UpdateTableData data=JSON.parseObject(json, UpdateTableData.class);
        //修改统计表
        StaTable table=staTableRepository.findOne(data.getId());
        if (!data.getTableName().equals(table.getTableName()))
        {
            //修改了表名
            if(staTableRepository.findByTableName(data.getTableName())!=null)
            {
                return new ReturnData(1,"操作失败","表名重复");
            }
            else
            {
                table.setTableName(data.getTableName());
            }
        }
        table.setRemarks(data.getTableMsg());
        staTableRepository.save(table);

        //修改统计表元素
        if (data.getUpdateTableParam()!=null)
        {
            for (StatisticTableParam p:data.getUpdateTableParam())
            {
                StatisticTableParam param=staTableParamService.findById(p.getId());
                param.setValue(table.getTableName(), p.getParamName(), p.getIsUse(), p.getKeyName(), p.getParamType(), p.getConnectType(), p.getConnectName(), p.getFixedValue());
                staTableParamService.save(param);
            }
        }
        if (data.getAddTableParam()!=null)
        {
            for (StatisticTableParam p:data.getAddTableParam())
            {
                staTableParamService.addTableParam(table.getTableName(), p);
                //表的统计项增加
                Integer num=table.getDataNum()+1;
                table.setDataNum(num);
                staTableRepository.save(table);
            }
        }
        if (data.getDeleteTableParam()!=null)
        {
            for (StatisticTableParam p:data.getDeleteTableParam())
            {
                staTableParamService.deleteById(p.getId());
                //表的统计项减少
                Integer num=table.getDataNum()-1;
                table.setDataNum(num);
                staTableRepository.save(table);
            }
        }

        return new ReturnData(0,"操作成功",null);
    }

    //返回统计表的列表
    @Override
    public ReturnData getTableList()
    {
        List<StaTable> list = staTableRepository.findAll();
        return new ReturnData(0,"操作成功",list);
    }

    //查看统计列表数据详情
    @Override
    public ReturnData seeTable(Integer id) throws ScriptException
    {
        String tableName=staTableRepository.findOne(id).getTableName();
        Map<String, SeeTableData> list=staTableParamService.seeTable(tableName);
        return new ReturnData(0,"操作成功",list);
    }

    //查看统计表详情，返回统计表的所有元素(进入统计表修改页面)
    @Override
    public ReturnData getTableDetail(Integer id)
    {
        TableDetail detail=new TableDetail();
        StaTable table=staTableRepository.findOne(id);
        detail.setId(table.getId());
        detail.setTableName(table.getTableName());
        detail.setRemarks(table.getRemarks());
        detail.setParamList(staTableParamService.getTableParams(table.getTableName()));
        return new ReturnData(0,"操作成功",detail);
    }

    //查询表（模糊查询）
    @Override
    public ReturnData findTable(String tableName)
    {
        StaTable table=staTableRepository.findTable(tableName);
        return new ReturnData(0,"操作成功",table);
    }

    //导出报表
    @Override
    public ReturnData exportTable(Integer id, HttpServletResponse response) throws Exception
    {
        StaTable table=staTableRepository.findOne(id);
        List<String> titles=new ArrayList<>();
        Map<String, SeeTableData> map = (Map<String, SeeTableData>) seeTable(id).getData();
        for (Map.Entry<String,SeeTableData> entry:map.entrySet())
        {
            titles.add(entry.getKey());
        }

        //具体导出报表内容待实现
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(table.getTableName());
        ExcelUtil.createTitle(workbook,sheet,titles);

        //设置日期、格式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        //新增数据行，并且设置单元格数据
        int totalRow=map.get(titles.get(0)).getData().size();   //数据表的总行数（不算表头）
        //将所有设备的deviceNum存入列表
        Map<String,String> dataMap=map.get(titles.get(0)).getData();
        List<String> deviceNums=new ArrayList<>();
        for (Map.Entry<String,String> entry:dataMap.entrySet())
        {
            deviceNums.add(entry.getKey());
        }
        int rowNum=1;
        for (int i=0;i<totalRow;i++)
        {
            HSSFRow row = sheet.createRow(rowNum);
            for (int j=0;j<titles.size();j++)
            {
                row.createCell(j).setCellValue(map.get(titles.get(j)).getData().get(deviceNums.get(i)));
            }
            rowNum++;
        }

        String fileName = table.getTableName()+".xls";

        //生成excel文件
        ExcelUtil.buildExcelFile(fileName, workbook);

        //浏览器下载excel
        ExcelUtil.buildExcelDocument(fileName,workbook,response);

        return new ReturnData(0,"操作成功",null);
    }
}
