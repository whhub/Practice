package com.mj.scada.service;

import com.alibaba.fastjson.JSON;
import com.mj.scada.pojo.PermissionDo;
import com.mj.scada.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-05-13 10:18
 */
@Service
public class PermissionServiceImpl implements PermissionService
{
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<PermissionDo> findAllPermission()
    {
        List<com.mj.scada.bean.domain.PermissionDo> list = permissionRepository.findAll();
        String json= JSON.toJSONString(list);
        List<PermissionDo> permissionDos=JSON.parseArray(json, PermissionDo.class);
        return permissionDos;
    }
}
