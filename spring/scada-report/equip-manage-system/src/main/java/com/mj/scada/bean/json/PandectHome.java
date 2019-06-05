package com.mj.scada.bean.json;

import lombok.Data;

import java.util.Map;

/*
 *Author:ZouHeng
 *Des:设备详情首页返回的信息
 *Date:2019-05-15 14:38
 */
@Data
public class PandectHome
{
    private Map<String, Map<String, String>> equipMap;
    private PandectData pandectData;

    public PandectHome(Map<String, Map<String, String>> equipMap, PandectData pandectData)
    {
        this.equipMap = equipMap;
        this.pandectData = pandectData;
    }
}
