package com.mj.scada.service;

import com.mj.scada.bean.SignalData;
import com.mj.scada.bean.domain.doc.Doc;
import com.mj.scada.bean.json.PandectData;
import com.mj.scada.bean.json.PandectHome;
import com.mj.scada.bean.json.ReturnData;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-25 14:45
 */
public interface EquipInfoService
{
    ReturnData home();
    ReturnData uploadImg(MultipartFile file, String deviceNum);
    ReturnData connect(String deviceNum,Integer docId);
    ReturnData disconnect(String deviceNum,Integer docId);
    ReturnData downFile(String docName, HttpServletResponse response);
    ReturnData getEquipDoc(String deviceNum);
    ReturnData getRealTimeSignal(String deviceNum);
    ReturnData pandect(String deviceNum);
}
