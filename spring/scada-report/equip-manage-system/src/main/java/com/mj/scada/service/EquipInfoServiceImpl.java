package com.mj.scada.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mj.scada.bean.domain.doc.Doc;
import com.mj.scada.bean.domain.ledger.EquipLedger;
import com.mj.scada.bean.json.PandectData;
import com.mj.scada.bean.json.PandectHome;
import com.mj.scada.bean.json.ReturnData;
import com.mj.scada.minio.MinioUtils;
import com.mj.scada.rabbitmq.Consumer;
import com.mj.scada.bean.Signal;
import com.mj.scada.bean.SignalData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-25 14:46
 */
@Service
public class EquipInfoServiceImpl implements EquipInfoService
{
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private EquipLedgerService equipLedgerService;
    @Autowired
    private DocService docService;
    @Autowired
    private DocConnectService docConnectService;
    @Autowired
    private TimeTodayService timeTodayService;
    @Autowired
    private EquipService equipService;

    @Value("${minio.url}")
    private String minioUrl;
    @Value("${minio.username}")
    private String minioUsername;
    @Value("${minio.password}")
    private String minioPassword;
    @Value("${minio.docBucket}")
    private String docBucket;
    private MinioUtils minioUtils;

    //设备详情首页
    @Override
    public ReturnData home()
    {
        //设备列表
        Map<String, Map<String, String>> equipMap = equipService.getEquipList();
        //获取map中的第一个元素
        List<String> deviceNums = new ArrayList<>();
        for (Map.Entry<String, Map<String, String>> entry : equipMap.entrySet())
        {
            for (Map.Entry<String, String> entry1 : entry.getValue().entrySet())
            {
                deviceNums.add(entry1.getValue());
            }
        }
        String deviceNum = deviceNums.get(0);

        //设备总览信息
        PandectData pandectData = (PandectData) pandect(deviceNum).getData();

        PandectHome pandectHome = new PandectHome(equipMap, pandectData);
        return new ReturnData(0,"操作成功",pandectHome);
    }

    //设备总览信息
    @Override
    public ReturnData pandect(String deviceNum)
    {
        //设备名称
        String name = equipService.getEquipNameMap().get(deviceNum);

        //设备开关机状态、故障状态
        Integer startup=equipService.getEquipsStartup().get(deviceNum);
        Integer fault=equipService.getEquipsFault().get(deviceNum);

        //设备台账
        EquipLedger ledger = equipLedgerService.getLedger(deviceNum);

        //设备图片
        if (minioUtils == null)
        {
            minioUtils = new MinioUtils(minioUrl, minioUsername, minioPassword);
        }
        String imgName="";
        try
        {
            imgName=minioUtils.listObjectsInfo(deviceNum).get(0).split("/")[1];
        }
        catch (Exception e) { }
        String imageUrl = minioUtils.imgUrl(deviceNum, imgName);

        //设备开关机时间（当天）
        Map<String, Integer> timeMap = new HashMap<>();
        Integer startupTime = timeTodayService.getTodayStartupTime(deviceNum);
        Integer closeTime = timeTodayService.getTodayCloseTime(deviceNum);
        timeMap.put("startup", startupTime);
        timeMap.put("close", closeTime);

        PandectData pandectData = new PandectData(name, startup,fault, ledger, imageUrl, timeMap);
        return new ReturnData(0,"操作成功",pandectData);
    }

    //上传设备图片
    @Override
    public ReturnData uploadImg(MultipartFile file, String deviceNum)
    {
        //将文件上传到Minio
        if (minioUtils == null)
        {
            minioUtils = new MinioUtils(minioUrl, minioUsername, minioPassword);
        }
        String fileOriName = file.getOriginalFilename();
        return minioUtils.upload(file, deviceNum,fileOriName);
    }

    //关联文件
    @Override
    public ReturnData connect(String deviceNum, Integer docId)
    {
        docConnectService.add(docId, deviceNum);
        return new ReturnData(0, "操作成功", null);
    }

    //删除关联
    @Override
    public ReturnData disconnect(String deviceNum, Integer docId)
    {
        docConnectService.del(docId, deviceNum);
        return new ReturnData(0, "操作成功", null);
    }

    //下载文件
    @Override
    public ReturnData downFile(String docName, HttpServletResponse response)
    {
        //从minio下载文件
        if (minioUtils == null)
        {
            minioUtils = new MinioUtils(minioUrl, minioUsername, minioPassword);
        }
        try
        {
            minioUtils.downloadFile(docBucket, docName, response);
            System.out.println("成功下载文件!");
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        //更新数据库中文件下载次数
        docService.downloadFile(docName);
        return new ReturnData(0,"操作成功",null);
    }

    /**
     * 得到设备的所有的档案及档案的相关信息
     */
    @Override
    public ReturnData getEquipDoc(String deviceNum)
    {
        if (minioUtils == null)
        {
            minioUtils = new MinioUtils(minioUrl, minioUsername, minioPassword);
        }
        List<Doc> docList = new ArrayList<>();
        List<Integer> docIdList = docConnectService.findAll(deviceNum);
        for (Integer docId : docIdList)
        {
            Doc doc = docService.findById(docId);
            docList.add(doc);
        }
        return new ReturnData(0,"操作成功",docList);
    }

    //获取设备实时数据（设备详情/实时数据，从RabbitMQ获取）
    @Override
    public ReturnData getRealTimeSignal(String deviceNum)
    {
        SignalData data = null;
        //遍历从RabbitMQ接收到的消息
        for (Map.Entry<String, JSONObject> entry : Consumer.signalMap.entrySet())
        {
            if (entry.getKey().equals(deviceNum))
            {
                Signal signal = JSON.parseObject(entry.getValue().toJSONString(), Signal.class);
                data = signal.getData();
            }

        }
        return new ReturnData(0,"操作成功",data);
    }
}
