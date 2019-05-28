package com.mj.scada.repository;

import com.mj.scada.bean.domain.algorithm.AlgorithmNode;
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
public interface AlgorithmNodeRepository extends JpaRepository<AlgorithmNode,Integer>
{
    List<AlgorithmNode> findByModelId(Integer modelId);
    AlgorithmNode findByModelIdAndNodeName(Integer modelId,String nodeName);
}
