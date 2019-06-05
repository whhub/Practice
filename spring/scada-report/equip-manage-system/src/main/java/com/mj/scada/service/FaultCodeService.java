package com.mj.scada.service;

import com.mj.scada.bean.json.ReturnData;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-05-29 13:49
 */
public interface FaultCodeService
{
    ReturnData add(String json);
    ReturnData del(Integer id);
    ReturnData update(String json);
    ReturnData getList();
}
