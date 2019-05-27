package com.example.SensorDataGenerator;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class State {

    // 开关机状态
    @JSONField
    private boolean startup;

    //计划工作时间，单位：min
    @JSONField
    private int planTotalTime;

    //生产速率，单位：min/件
    @JSONField
    private double planProductRate;

    //良品数，单位：件
    @JSONField
    private int realGoodNum;

    //总产量
    private int realTotalNum;
}
