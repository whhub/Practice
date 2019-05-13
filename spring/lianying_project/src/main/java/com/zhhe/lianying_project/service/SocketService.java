package com.zhhe.lianying_project.service;

import com.zhhe.lianying_project.bean.Data;
import com.zhhe.lianying_project.bean.RestEquipListData;

import javax.servlet.http.HttpServletRequest;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-01 17:50
 */
public interface SocketService
{
    String sendStartupAndShutdownList(Integer num);
    void sendMsg();
}
