package com.mj.scada.minio;

import com.mj.scada.bean.json.ReturnData;
import io.minio.MinioClient;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Item;
import org.springframework.web.multipart.MultipartFile;
import org.xmlpull.v1.XmlPullParserException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-03-25 11:03
 */
public class MinioUtils
{
    private MinioClient minioClient;


    public MinioUtils(String url, String username, String password)
    {
        init(url, username, password);
    }

    //初始化MinioClient
    private void init(String url, String username, String password)
    {
        try
        {
            minioClient = new MinioClient(url, username, password);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //创建文件夹
    private void createBucket(String bucketName)
    {
        try
        {
            boolean isExist = minioClient.bucketExists(bucketName);
            if (isExist)
            {
                System.out.println("Bucket already exists.");
            }
            else
            {
                // Make a new bucket called asiatrip to hold a zip file of photos.
                minioClient.makeBucket(bucketName);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //上传文件
    public ReturnData upload(MultipartFile file,String bucketName)
    {
        try
        {
            boolean found = minioClient.bucketExists(bucketName);
            if (!found)
            {
                minioClient.makeBucket(bucketName);
            }
            String fileName = file.getOriginalFilename();
            InputStream inputStream = file.getInputStream();
            String contentType = file.getContentType();
            minioClient.putObject(bucketName, fileName, inputStream, contentType);
        }
        catch (Exception e)
        {
            return new ReturnData(1,"上传失败:"+e.getMessage(),null);
        }
        return new ReturnData(0,"操作成功",null);
    }

    public void delDoc(String bucketName,String objName)
    {
        try
        {
            minioClient.removeObject(bucketName, objName);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //返回图片的url
    public String imgUrl(String bucketName,String imgName)
    {
        try
        {
            return minioClient.presignedGetObject(bucketName, imgName);
        }
        catch (Exception e)
        {
            return "获取图片失败:"+e.getMessage();
        }
    }

    //下载文件
    public void downloadFile(String deviceNum, String docName, HttpServletResponse response) throws IOException
    {
        InputStream stream = null;
        OutputStream out = null;
        try
        {
            minioClient.statObject(deviceNum, docName);
            stream = minioClient.getObject(deviceNum, docName);
            int len = 0;
            byte[] buffer = new byte[1024];
            out = response.getOutputStream();
            response.reset();
            response.addHeader("Content-Disposition", " attachment;filename=" + deviceNum);
            response.setContentType("application/octet-stream");
            while ((len = stream.read(buffer)) > 0)
            {
                out.write(buffer, 0, len);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            out.flush();
            out.close();
            stream.close();
        }
    }

    /**
     * 列出存储桶中的所有对象
     * @return 列表中的元素："2019-03-29/1.png"
     */
    public List<String> listObjectsInfo(String deviceNum) throws Exception
    {
        boolean found = minioClient.bucketExists(deviceNum);
        List<String> list = new ArrayList<>();
        if (found)
        {
            Iterable<Result<Item>> myObjects = minioClient.listObjects(deviceNum);
            for (Result<Item> result : myObjects)
            {
                Item item = result.get();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String date = formatter.format(item.lastModified());
//                System.out.println(date + ", " + item.size() + ", " + item.objectName());
                list.add(date + "/" + item.objectName());
            }
        }
        else
        {
            System.out.println("bucket does not exist");
        }
        return list;
    }

    /**
     * 获取文件信息
     * @return "2019-03-29/1.png":上传日期和文件名
     */
    public String getFilesInfo(String deviceNum,String fileName) throws Exception
    {
        boolean found = minioClient.bucketExists(deviceNum);
        String info="";
        if (found)
        {
            Iterable<Result<Item>> myObjects = minioClient.listObjects(deviceNum);
            for (Result<Item> result : myObjects)
            {
                Item item = result.get();
                if (item.objectName().equals(fileName))
                {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String date = formatter.format(item.lastModified());
                    info=date+"/"+ item.objectName();
                }
            }
        }
        else
        {
            System.out.println("bucket does not exist");
        }
        return info;
    }

}
