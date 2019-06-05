package com.mj.scada.bean.domain.doc;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/*
 *Author:ZouHeng
 *Des:设备上传的文件
 *Date:2019-03-29 11:40
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "doc")
public class Doc
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String docName;
    private String type;        //文档类型，对应设备类别
    private String classify;    //文档分类，对应设备品牌
    private String createTime;
    private Integer downloadNum;
    private String author;
    private String label;           //标签
    private String introduction;  //简介

    public Doc()
    {
    }

    public Doc(String docName, String type, String classify, String createTime, Integer downloadNum, String author, String label, String introduction)
    {
        this.docName = docName;
        this.type = type;
        this.classify = classify;
        this.createTime = createTime;
        this.downloadNum = downloadNum;
        this.author = author;
        this.label = label;
        this.introduction = introduction;
    }
}
