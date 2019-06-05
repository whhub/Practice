package com.mj.scada.controller;

import com.mj.scada.service.AlgorithmNodeService;
import com.mj.scada.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-04-11 18:09
 */
@RestController
public class TestController
{
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private AlgorithmNodeService algorithmNodeService;


    @RequestMapping("test2")
    public String test2()
    {
//        algorithmNodeService.getValue(1, "sort");
        return "succ";
    }
}
