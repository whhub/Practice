package com.mj.scada.repository;

import com.mj.scada.bean.domain.statistics.StatisticTableParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-04-10 14:41
 */
@Repository
public interface StatisticTableParamRepository extends JpaRepository<StatisticTableParam,Integer>
{
    @Query(value = "select max(id) for statistic_table",nativeQuery = true)
    Integer findMaxId();

    List<StatisticTableParam> findByTableName(String tableName);
}
