package com.mj.scada.bean.json;

import com.mj.scada.bean.domain.doc.Doc;
import lombok.Data;

import java.util.Map;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-05-17 15:14
 */
@Data
public class DocDetail
{
    private Doc doc;
    private Map<String,String> equipMap;

    public DocDetail(Doc doc, Map<String, String> equipMap)
    {
        this.doc = doc;
        this.equipMap = equipMap;
    }
}
