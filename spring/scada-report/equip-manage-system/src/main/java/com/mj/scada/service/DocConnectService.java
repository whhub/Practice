package com.mj.scada.service;

import redis.clients.jedis.BinaryClient;

import java.util.List;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-05-16 15:24
 */
public interface DocConnectService
{
    void add(Integer docId,String deviceNum);
    void del(Integer docId,String deviceNum);
    List<Integer> findAll(String deviceNum);
    boolean haveConnect(Integer docId);
    List<String> findEquips(Integer docId);
}
