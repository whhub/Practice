package com.mj.scada.service;

import com.mj.scada.bean.domain.statistics.StaTable;
import com.mj.scada.bean.json.ReturnData;
import com.mj.scada.bean.json.SeeTableData;
import com.mj.scada.bean.json.TableDetail;

import javax.script.ScriptException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-04-30 14:55
 */
public interface StaTableService
{
    ReturnData addStaTable(String tableName, String remarks);
    ReturnData deleteTable(Integer id);
    ReturnData updateTable(String json);
    ReturnData getTableList();
    ReturnData seeTable(Integer id) throws ScriptException;
    ReturnData getTableDetail(Integer id);
    ReturnData findTable(String tableName);
    ReturnData exportTable(Integer id, HttpServletResponse response) throws Exception;
}
