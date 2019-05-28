package com.mj.scada.service;

import com.mj.scada.bean.RestEquipList;
import com.mj.scada.bean.domain.ledger.EquipTotal;
import com.mj.scada.repository.EquipTotalRepository;
import com.mj.scada.config.ConfigBean;
import com.mj.scada.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.*;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-20 9:45
 */
@Service
public class EquipTotalServiceImpl implements EquipTotalService
{
    @Autowired
    private EquipTotalRepository equipTotalRepository;
    @Autowired
    private RestTemplate restTemplate;

    //得到最近五年的设备总数
    @Override
    @Transactional
    public Map<Integer,Integer> getLatestEquipTotal()
    {
        saveThisYearData();

        List<EquipTotal> totalList=equipTotalRepository.findAll();
        Map<Integer,Integer> map = new HashMap<>();
        for (int i=totalList.size()-1;i>=0;i--)
        {
           map.put(totalList.get(i).getYear(), totalList.get(i).getTotal());
        }
        return map;
    }

    //存储今年的数据(往年的数据需要提前存入数据库)
    @Override
    @Transactional
    public void saveThisYearData()
    {
        //请求Rest接口，得到设备总数
        RestEquipList restEquipList= restTemplate.getForEntity(ConfigBean.equipListUrl, RestEquipList.class).getBody();
        Integer total=restEquipList.getData().size();
        //得到今年年份
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Integer year=Integer.parseInt(df.format(new Date()));
        Integer year= Util.getCurrentYearOrTime("year");

        if (equipTotalRepository.findByYear(year)==null)
        {
            EquipTotal equipTotal=new EquipTotal(total,year);
            equipTotalRepository.save(equipTotal);
        }
        else
        {
            EquipTotal equipTotal=equipTotalRepository.findByYear(year);
            equipTotal.setTotal(total);
            equipTotalRepository.save(equipTotal);
        }
    }

}
