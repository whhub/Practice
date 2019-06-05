package com.mj.scada.service;

import com.alibaba.fastjson.JSON;
import com.mj.scada.bean.domain.MaintainEvent;
import com.mj.scada.bean.domain.MaintainPlan;
import com.mj.scada.repository.MaintainPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-19 14:41
 */
@Service
public class MaintainPlanServiceImpl implements MaintainPlanService
{
    @Autowired
    private MaintainPlanRepository maintainPlanRepository;
    @Autowired
    private MaintainEventService maintaineventService;

    //返回所有维修计划的json
    @Override
    public String getMaintainPlan()
    {
        List<MaintainPlan> maintainPlanList=maintainPlanRepository.findAll();
        List<MaintainEvent> maintainEventList = maintaineventService.findAll();
        Map<String, MaintainEvent> maintainInfoMap=new HashMap<>();
        for (MaintainEvent info: maintainEventList)
        {
            maintainInfoMap.put(info.getId().toString(), info);
        }

        List<Map<String,String>> list = new ArrayList<>();
        for (MaintainPlan plan:maintainPlanList)
        {
            Map<String,String> map=new HashMap<>();
            map.put("color", maintainInfoMap.get(plan.getMaintainInfo_id().toString()).getColor());
            map.put("msg", maintainInfoMap.get(plan.getMaintainInfo_id().toString()).getMsg());
            map.put("date", plan.getDate());
            map.put("status", plan.getStatus());
            list.add(map);
        }
        return JSON.toJSONString(list);
    }


}
