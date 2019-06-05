package com.mj.scada.service;

import com.mj.scada.bean.domain.SysLog;
import com.mj.scada.repository.SysLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-06-01 16:10
 */
@Service
public class SysLogServiceImpl implements SysLogService
{
    @Autowired
    private SysLogRepository sysLogRepository;

    @Override
    public void save(SysLog sysLog)
    {
        sysLogRepository.save(sysLog);
    }
}
