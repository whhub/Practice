package com.zhhe.lianying_project.service;

import com.zhhe.lianying_project.bean.entity.StartupAndShutdown;

import java.util.List;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-12 14:20
 */
public interface StartupAndShutdownService
{
    String selectNum(Integer time);
    void save(StartupAndShutdown startupAndShutdown);
    List<StartupAndShutdown> findAll();
}
