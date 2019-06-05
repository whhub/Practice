package com.mj.scada.repository;

import com.mj.scada.bean.domain.statistics.StaTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-04-30 14:54
 */
@Repository
public interface StaTableRepository extends JpaRepository<StaTable,Integer>
{
    StaTable findByTableName(String tableName);

    @Query(value = "select * from sta_table where table_name like %?1%",nativeQuery = true)
    StaTable findTable(String tableName);
}
