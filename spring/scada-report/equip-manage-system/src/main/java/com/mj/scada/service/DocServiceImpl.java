package com.mj.scada.service;

import com.alibaba.fastjson.JSON;
import com.mj.scada.bean.domain.doc.Doc;
import com.mj.scada.bean.domain.type.EquipBrand;
import com.mj.scada.bean.domain.type.EquipType;
import com.mj.scada.bean.json.DocDetail;
import com.mj.scada.bean.json.ReturnData;
import com.mj.scada.common.AuthConst;
import com.mj.scada.minio.MinioUtils;
import com.mj.scada.pojo.User;
import com.mj.scada.repository.DocRepository;
import com.mj.scada.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-29 13:57
 */
@Service
public class DocServiceImpl implements DocService
{
    @Autowired
    private DocRepository docRepository;
    @Autowired
    private DocConnectService docConnectService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private EquipTypeService equipTypeService;
    @Autowired
    private EquipBrandService equipBrandService;
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

    @Override
    @Transactional
    public ReturnData add(MultipartFile file, String docName, String type, String classify, String label, String introduction, HttpServletRequest request)
    {
        //将文件上传到Minio
        if (minioUtils == null)
        {
            minioUtils = new MinioUtils(minioUrl, minioUsername, minioPassword);
        }
        try
        {
            minioUtils.upload(file, docBucket,docName);
        }
        catch (Exception e)
        {
            return new ReturnData(1, "上传失败", null);
        }

        //将信息存入数据库
        if (docRepository.findByDocName(docName) == null)
        {
            //从session中获取用户名
            User user=(User)request.getSession().getAttribute(AuthConst.LOGIN_USER);
            String author=user.getUsername();

            Doc doc = new Doc(docName, type, classify, Util.getCurrentDateAndTime(), 0, author,label, introduction);
            docRepository.save(doc);
            return new ReturnData(0, "操作成功", null);
        }
        else
        {
            return new ReturnData(1, "上传失败，文件名重复", null);
        }
    }

    //模糊查询
    @Override
    public ReturnData fuzzyFind(String docName)
    {
        List<Doc> list = docRepository.fuzzyFind(docName);
        return new ReturnData(0, "操作成功", list);
    }

    @Override
    public ReturnData find(Integer id)
    {
        return new ReturnData(0,"操作成功",docRepository.findOne(id));
    }

    @Override
    public ReturnData findAll()
    {
        List<Doc> list = docRepository.findAll();
        return new ReturnData(0, "操作成功", list);
    }

    //文档详情
    @Override
    public ReturnData detail(Integer id)
    {
        Doc doc = docRepository.findOne(id);
        List<String> list = docConnectService.findEquips(id);
        Map<String, String> map = new HashMap<>();
        for (String s : list)
        {
            map.put(s, equipService.getEquipNameMap().get(s));
        }
        DocDetail docDetail = new DocDetail(doc, map);
        return new ReturnData(0, "操作成功", docDetail);
    }

    @Override
    public ReturnData update(String json)
    {
        Doc doc = JSON.parseObject(json, Doc.class);
        docRepository.save(doc);
        return new ReturnData(0, "操作成功", null);
    }

    //统计下载次数
    @Override
    @Transactional
    public void downloadFile(String docName)
    {
        Doc doc = docRepository.findByDocName(docName);
        Integer num = doc.getDownloadNum() + 1;
        doc.setDownloadNum(num);
        docRepository.save(doc);
    }

    @Override
    public Doc findById(Integer id)
    {
        return docRepository.findOne(id);
    }

    @Override
    public ReturnData delDoc(Integer docId)
    {
        //查看文档是否有关联
        boolean isConn = docConnectService.haveConnect(docId);
        if (isConn) //有关联，不能删除
        {
            return new ReturnData(1, "文档有关联，无法删除!", null);
        }
        else        //无关联，可以删除
        {
            //minio上删除文件
            if (minioUtils == null)
            {
                minioUtils = new MinioUtils(minioUrl, minioUsername, minioPassword);
            }
            Doc doc = docRepository.findOne(docId);
            minioUtils.delDoc(docBucket, doc.getDocName());

            //数据库中删除文档
            docRepository.delete(docId);
            return new ReturnData(0, "操作成功", null);
        }
    }

    @Override
    public ReturnData findByTypeAndClassify(String type, String classify)
    {
        List<Doc> list = docRepository.findByTypeAndClassify(type, classify);
        return new ReturnData(0, "操作成功", list);
    }
}
