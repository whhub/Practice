package com.mj.scada.bean.domain.info;

import lombok.Data;

import javax.persistence.*;

/*
 *Author:ZouHeng
 *Des:文档关联的设备
 *Date:2019-05-16 15:21
 */
@Data
@Entity
@Table(name = "doc_connect")
public class DocConnect
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer docId;
    private String deviceNum;

    public DocConnect()
    {
    }

    public DocConnect(Integer docId, String deviceNum)
    {
        this.docId = docId;
        this.deviceNum = deviceNum;
    }
}
