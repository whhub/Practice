package com.example.SensorDataGenerator;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Process {

    // 水压
    @JSONField
    private double waterPressure;

    // 水流
    @JSONField
    private double flow;
}
