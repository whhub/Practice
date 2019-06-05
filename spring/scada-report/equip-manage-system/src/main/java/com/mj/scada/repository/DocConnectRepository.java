package com.mj.scada.repository;

import com.mj.scada.bean.domain.info.DocConnect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-05-16 15:24
 */
@Repository
public interface DocConnectRepository extends JpaRepository<DocConnect,Integer>
{
    DocConnect findByDocIdAndDeviceNum(Integer docId,String deviceNum);
    List<DocConnect> findByDeviceNum(String deviceNum);
    List<DocConnect> findByDocId(Integer docId);
}
