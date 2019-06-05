package com.mj.scada.bean.json;

import com.mj.scada.bean.SignalData;
import com.mj.scada.bean.domain.ledger.EquipLedger;
import lombok.Data;

import java.util.Map;

/*
 *Author:ZouHeng
 *Des:设备详情中单个设备的总览信息
 *Date:2019-05-15 14:21
 */
@Data
public class PandectData
{
    private String name;
    private Integer startup;
    private Integer fault;
    private EquipLedger ledger;
    private String imageUrl;
    private Map<String,Integer> timeMap; //开关机时间(单位：分)

    public PandectData(String name, Integer startup, Integer fault, EquipLedger ledger, String imageUrl, Map<String, Integer> timeMap)
    {
        this.name = name;
        this.startup = startup;
        this.fault = fault;
        this.ledger = ledger;
        this.imageUrl = imageUrl;
        this.timeMap = timeMap;
    }
}
