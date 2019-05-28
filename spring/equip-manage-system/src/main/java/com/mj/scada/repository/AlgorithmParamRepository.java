package com.mj.scada.repository;

import com.mj.scada.bean.domain.algorithm.AlgorithmParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-04-02 9:58
 */
@Repository
public interface AlgorithmParamRepository extends JpaRepository<AlgorithmParam,Integer>
{
    List<AlgorithmParam> findByNodeId(Integer nodeId);

    @Query(value = "select * from algorithm_param where keyName like '%?1%'",nativeQuery = true)
    List<AlgorithmParam> findInFuzzy(String name);

    List<AlgorithmParam> findByConnectType(String type);

    @Query(value = "select * from algorithm_param where type=?1 and name like '%?2%'",nativeQuery = true)
    List<AlgorithmParam> findByTypeInFuzzy(String type,String name);

    AlgorithmParam findByNodeIdAndParamName(Integer nodeId,String paramName);
}
