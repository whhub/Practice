package com.mj.scada.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mj.scada.bean.Signal;
import com.mj.scada.bean.SignalData;
import com.mj.scada.bean.json.ReturnData;
import com.mj.scada.rabbitmq.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-05-23 11:09
 */
@Service
public class HomeServiceImpl implements HomeService
{
    @Autowired
    private EquipLedgerService equipLedgerService;

    @Override
    public ReturnData status()
    {
        Integer openNum=0,closeNum=0,faultNum=0;   //开机、关机、故障
        //遍历从RabbitMQ接收到的消息
        for (Map.Entry<String, JSONObject> entry : Consumer.signalMap.entrySet())
        {
            Signal signal = JSON.parseObject(entry.getValue().toJSONString(), Signal.class);
            Map<String,String> stateMap = signal.getData().getState();
            if (stateMap!=null)
            {
                if (stateMap.containsKey("startup"))
                {
                    if (stateMap.get("startup").equals(1))
                    {
                        openNum++;
                    }
                    else
                    {
                        closeNum++;
                    }
                }
                if (stateMap.containsKey("fault"))
                {
                    if (stateMap.get("fault").equals("1"))
                    {
                        faultNum++;
                    }
                }
            }
        }
        Map<String,Integer> map = new HashMap<>();
        map.put("开机", openNum);
        map.put("关机", closeNum);
        map.put("故障", faultNum);
        return new ReturnData(0,"成操作功",map);
    }

    @Override
    public ReturnData types()
    {
        Map<String,Integer> map = equipLedgerService.getEquipSort();
        return new ReturnData(0,"成操作功",map);
    }

    @Override
    public ReturnData statusInfo()
    {
        //遍历从RabbitMQ接收到的消息
        for (Map.Entry<String, JSONObject> entry : Consumer.signalMap.entrySet())
        {
            Signal signal = JSON.parseObject(entry.getValue().toJSONString(), Signal.class);
        }
        return null;
    }
}
