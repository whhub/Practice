package com.mj.scada.service;

import com.mj.scada.bean.domain.FaultEvent;
import com.mj.scada.bean.json.ReturnData;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-05-29 16:03
 */
public interface FaultEventService
{
    void eventStart(String deviceNum,String faultCode);
    void eventEnd(String deviceNum);
    List<FaultEvent> getTodayList(String deviceNum,String todayDate);
    FaultEvent getFault(String deviceNum);
    ReturnData sectionList(String deviceNum,String startDate, String endDate) throws ParseException;
    ReturnData todayList(String deviceNum);
    ReturnData weekTotal(String deviceNum,Integer index);
    ReturnData thisWeekTotal(String deviceNum);
}
