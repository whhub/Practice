package com.mj.scada.service;

import com.mj.scada.bean.domain.info.DocConnect;
import com.mj.scada.repository.DocConnectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-05-16 15:25
 */
@Service
public class DocConnectServiceImpl implements DocConnectService
{
    @Autowired
    private DocConnectRepository docConnectRepository;

    //添加关联
    @Override
    @Transactional
    public void add(Integer docId, String deviceNum)
    {
        DocConnect connect=new DocConnect(docId,deviceNum);
        docConnectRepository.save(connect);
    }

    //删除关联
    @Override
    @Transactional
    public void del(Integer docId, String deviceNum)
    {
        DocConnect connect=docConnectRepository.findByDocIdAndDeviceNum(docId, deviceNum);
        docConnectRepository.delete(connect);
    }

    @Override
    public List<Integer> findAll(String deviceNum)
    {
        List<DocConnect> docConnects=docConnectRepository.findByDeviceNum(deviceNum);
        List<Integer> list = new ArrayList<>();
        for (DocConnect d:docConnects)
        {
            list.add(d.getDocId());
        }
        return list;
    }

    //判断文档是否还有关联
    @Override
    public boolean haveConnect(Integer docId)
    {
        List<DocConnect> list=docConnectRepository.findByDocId(docId);
        if (list==null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    //得到文档关联的所有设备
    @Override
    public List<String> findEquips(Integer docId)
    {
        List<DocConnect> docConnects=docConnectRepository.findByDocId(docId);
        List<String> list = new ArrayList<>();
        for (DocConnect d:docConnects)
        {
            list.add(d.getDeviceNum());
        }
        return list;
    }
}
