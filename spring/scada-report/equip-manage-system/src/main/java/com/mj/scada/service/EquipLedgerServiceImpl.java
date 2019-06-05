package com.mj.scada.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mj.scada.bean.RestEquipList;
import com.mj.scada.bean.RestEquipListData;
import com.mj.scada.bean.json.LedgerData;
import com.mj.scada.bean.json.ReturnData;
import com.mj.scada.config.ConfigBean;
import com.mj.scada.util.Util;
import com.mj.scada.bean.domain.ledger.EquipLedger;
import com.mj.scada.bean.EquipSort;
import com.mj.scada.repository.EquipLedgerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-18 14:37
 */
@Service
public class EquipLedgerServiceImpl implements EquipLedgerService
{
    @Autowired
    private EquipLedgerRepository equipLedgerRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private EquipTotalService equipTotalService;
    @Autowired
    private EquipService equipService;

    //从Rest接口同步设备通用信息，并存入数据库(在ListenEquip中调用)
    @Override
    @Transactional
    public ReturnData updateEquipLedgerFromRest()
    {
        //同步设备列表
        equipService.updateMap();

        RestEquipList restEquipList = restTemplate.getForEntity(ConfigBean.equipListUrl, RestEquipList.class).getBody();
        List<RestEquipListData> list = restEquipList.getData();
        for (RestEquipListData d : list)
        {
            //只同步通用信息
            EquipLedger ledger=null;
            if (equipLedgerRepository.findOne(d.getId())==null) //数据库中无记录
            {
                ledger = new EquipLedger();
                ledger.setId(d.getId());

            }
            else //数据库中有记录
            {
                ledger=equipLedgerRepository.findOne(d.getId());
            }
            ledger.setEquipName(equipService.getEquipNameMap().get(d.getDeviceNum())); //设备名称
            ledger.setEquipNum(d.getDeviceNum());               //设备编号
            ledger.setInstallSite(d.getDeviceArea());           //放置位置
            ledger.setEquipSort(equipService.getEquipSortMap().get(d.getDeviceNum())); //设备模型（类别）
            equipLedgerRepository.save(ledger);
        }
        List<EquipLedger> equipLedgers=equipLedgerRepository.findAll();
        return new ReturnData(0,"操作成功",equipLedgers);
    }

    //返回当前设备状态统计分析数据
    @Override
    public Map<String,String> analysisStatus()
    {
        List<EquipLedger> list = equipLedgerRepository.findAll();
        List<Integer> statusList = new ArrayList<>();
        for (EquipLedger info : list)
        {
            if (info.getStatus()!=null)
            {
                statusList.add(Integer.parseInt(info.getStatus()));
            }
        }
        int inUseNum = 0, nonUseNum = 0, scrapNum = 0, unUseNum = 0, totalNum = 0;
        for (int i : statusList)
        {
            switch (i)
            {
                case 0:
                    nonUseNum++;
                    break;
                case 1:
                    inUseNum++;
                    break;
                case 2:
                    unUseNum++;
                    break;
                case 3:
                    scrapNum++;
                    break;
            }
        }
        totalNum = inUseNum + nonUseNum + unUseNum + scrapNum;
//        int inUseRatio, nonUseRatio, unUseRatio, scrapRatio;
//        inUseRatio = (int) ((float) inUseNum / (float) totalNum) * 100;
//        nonUseRatio = (int) ((float) nonUseNum / (float) totalNum) * 100;
//        unUseRatio = (int) ((float) unUseNum / (float) totalNum) * 100;
//        scrapRatio = 100 - inUseRatio - nonUseRatio - unUseRatio;
        Map<String, String> map = new HashMap<>();
        map.put("在用", inUseNum + "");
        map.put("停用", nonUseNum + "");
        map.put("闲置", unUseNum + "");
        map.put("报废", scrapNum + "");
//        map.put("在用率", inUseRatio + "%");
//        map.put("停用率", nonUseRatio + "%");
//        map.put("闲置率", unUseRatio + "%");
//        map.put("报废率", scrapRatio + "%");
        return map;
    }

    //得到设备类别和数量
    @Override
    public Map<String, Integer> getEquipSort()
    {
        return equipService.getEquipSort();
    }

    //设备台账首页
    @Override
    public ReturnData home()
    {
        //最新5年设备数量统计
        Map<Integer, Integer> totalMap = equipTotalService.getLatestEquipTotal();
        //当前设备状态统计分析
        Map<String,String> statusMap=analysisStatus();
        //设备种类分析
        Map<String, Integer> sortMap=getEquipSort();
        //设备台账信息
        List<EquipLedger> ledgerMap=equipLedgerRepository.findAll();

        LedgerData ledgerData=new LedgerData(totalMap,statusMap,sortMap,ledgerMap);
        return new ReturnData(0,"操作成功",ledgerData);
    }

    //增
    @Override
    @Transactional
    public ReturnData add(String json)
    {
        EquipLedger ledger=JSON.parseObject(json, EquipLedger.class);
        ledger.setPropertyName();
        equipLedgerRepository.save(ledger);
        return new ReturnData(0,"操作成功",null);
    }

    //删
    @Override
    @Transactional
    public ReturnData delete(Integer id)
    {
        equipLedgerRepository.delete(id);
        return new ReturnData(0,"操作成功",null);
    }
    //改
    @Override
    @Transactional
    public ReturnData update(String json)
    {
        EquipLedger ledger = JSON.parseObject(json, EquipLedger.class);
        equipLedgerRepository.save(ledger);
        return new ReturnData(0,"操作成功",null);
    }

    @Override
    public ReturnData find(String status, String name)
    {
        if (name!=null)
        {
            List<EquipLedger> list = equipLedgerRepository.findByStatusAndPropertyName(status, name);
            return new ReturnData(0,"操作成功",list);
        }
        else
        {
            List<EquipLedger> list = equipLedgerRepository.findByStatus(status);
            return new ReturnData(0,"操作成功",list);
        }
    }

    @Override
    public EquipLedger getLedger(String deviceNum)
    {
        return equipLedgerRepository.findByEquipNum(deviceNum);
    }

    @Override
    public ReturnData findById(Integer id)
    {
        EquipLedger ledger=equipLedgerRepository.findOne(id);
        return new ReturnData(0,"操作成功",ledger);
    }


}
