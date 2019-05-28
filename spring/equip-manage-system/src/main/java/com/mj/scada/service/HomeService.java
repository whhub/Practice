package com.mj.scada.service;

import com.mj.scada.bean.json.ReturnData;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-05-23 11:09
 */
public interface HomeService
{
    ReturnData status();
    ReturnData types();
    ReturnData statusInfo();
}
