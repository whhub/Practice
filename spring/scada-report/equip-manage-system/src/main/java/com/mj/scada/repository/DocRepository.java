package com.mj.scada.repository;

import com.mj.scada.bean.domain.doc.Doc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-29 13:55
 */
@Repository
public interface DocRepository extends JpaRepository<Doc,Integer>
{
//    Doc findByDeviceNumAndDocName(String deviceNum, String docName);
    Doc findByDocName(String docName);
    @Query(value = "select * from doc where docName like ?1",nativeQuery = true)
    List<Doc> fuzzyFind(String docName);
    List<Doc> findByTypeAndClassify(String type,String classify);
}
