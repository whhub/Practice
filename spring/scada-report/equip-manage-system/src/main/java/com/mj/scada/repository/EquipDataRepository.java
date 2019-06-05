package com.mj.scada.repository;

import com.mj.scada.bean.domain.algorithm.EquipData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.BinaryClient;

import java.util.List;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-04-02 14:58
 */
@Repository
public interface EquipDataRepository extends JpaRepository<EquipData,Integer>
{
    EquipData findByDeviceNumAndTypeAndKeyName(String deviceNum,String type,String keyName);

    List<EquipData> findByType(String type);

    @Query(value = "select count(*) from equip_data",nativeQuery = true)
    Integer findSize();

    //查找导入类数据和开机时间
    EquipData findByTypeAndKeyName(String type,String keyName);

    List<EquipData> findByKeyName(String keyName);

    //模糊查询
    @Query(value = "select * from equip_data where keyName like '%?1%'",nativeQuery = true)
    List<EquipData> findInFuzzy(String keyName);
    @Query(value = "select * from equip_data where type=?1 and keyName like '%?2%'",nativeQuery = true)
    List<EquipData> findByTypeInFuzzy(String type,String keyName);

}
