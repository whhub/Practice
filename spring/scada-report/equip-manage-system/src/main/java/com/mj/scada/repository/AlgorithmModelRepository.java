package com.mj.scada.repository;

import com.mj.scada.bean.domain.algorithm.AlgorithmModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-04-01 16:16
 */
@Repository
public interface AlgorithmModelRepository extends JpaRepository<AlgorithmModel,Integer>
{
    AlgorithmModel findByModelName(String name);

    @Query(value = "select * from algorithm_model where modelName like ?1",nativeQuery = true)
    AlgorithmModel fuzzyFindModel(String name);
}
