package com.example.SensorDataGenerator;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Message {
    @JSONField
    private String deviceNum;

    @JSONField
    private Date date;

    @JSONField
    private Data data;
}
