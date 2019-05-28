package com.mj.scada.service;

import com.mj.scada.bean.domain.doc.Doc;
import com.mj.scada.bean.domain.type.EquipBrand;
import com.mj.scada.bean.domain.type.EquipType;
import com.mj.scada.bean.json.DocDetail;
import com.mj.scada.bean.json.ReturnData;
import org.hibernate.loader.custom.Return;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-29 13:57
 */
public interface DocService
{
    ReturnData add(MultipartFile file, String docName, String type, String classify, String author, String introduction);
    ReturnData fuzzyFind(String docName);
    ReturnData findAll();
    ReturnData detail(Integer id);
    ReturnData update(String json);
    void downloadFile(String docName);
    Doc findById(Integer id);
    ReturnData delDoc(Integer docId);
    ReturnData findByTypeAndClassify(String type,String classify);
}
